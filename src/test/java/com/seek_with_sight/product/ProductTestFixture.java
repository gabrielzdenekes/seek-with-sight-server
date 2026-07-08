package com.seek_with_sight.product;

import com.seek_with_sight.media.ImageTestFixture;
import com.seek_with_sight.product.domain.model.ProductStatus;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.BrandEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.seek_with_sight.utils.data.RequestResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@TestComponent
public class ProductTestFixture {
    @Autowired
    private CategoryJpaRepository categoryRepo;

    @Autowired
    private BrandJpaRepository brandRepo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ImageTestFixture imageTestFixture;

    public RequestResponseData<ProductRequest, ApiResponse<ProductResponse>> createProductWithNumberOfRelationships(int numberOfRelationships) throws Exception {
        var dto = createProductRequest(numberOfRelationships);
        var payload = objectMapper.writeValueAsString(dto);
        var request = post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload);
        var result = mockMvc.perform(request).andReturn();
        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<ProductResponse>>() {}
        );

        return new RequestResponseData<>(dto, apiResponse);
    }

    public RequestResponseData<ProductRequest, ApiResponse<ProductResponse>> createProduct() throws Exception {
        return createProductWithNumberOfRelationships(3);
    }

    public ApiResponse<ProductResponseWithDetails> getProductById(UUID id) throws Exception {
        var request = get("/api/products/" + id);
        var result = mockMvc.perform(request).andReturn();
        return objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );
    }

    private ProductRequest createProductRequest(int numberOfRelationships) {
        var productName = ProductTestDataUtils.productName();
        var categories = categoryRepo
                .findAll(Pageable.ofSize(1))
                .get()
                .toArray(CategoryEntity[]::new);
        var brand = brandRepo
                .findAll(Pageable.ofSize(1))
                .get()
                .toArray(BrandEntity[]::new);

        return new ProductRequest(
                productName,
                SlugGenerator.generate(productName),
                ProductTestDataUtils.shortDescription(),
                ProductTestDataUtils.description(),
                ProductStatus.ACTIVE,
                categories[0].getId(),
                brand[0].getId(),
                ProductTestDataUtils.price()
        );
    }

    private List<UUID> getImageIds(int count) throws Exception {
        var imageIds = new ArrayList<UUID>();

        for (int i = 0; i < count; i++) {
            var uploadResult = imageTestFixture.uploadImage();

            imageIds.add(uploadResult.getData().id());
        }

        return imageIds;
    }

    public static class SlugGenerator {
        private static final Pattern NON_LATIN = Pattern.compile("[^\\w_-]");
        private static final Pattern SEPARATORS = Pattern.compile("[\\s_]+");
        private static final Pattern MULTI_HYPHEN = Pattern.compile("-+");
        private static final Pattern EDGES_HYPHEN = Pattern.compile("^-|-$");

        public static String generate(String input) {
            if (input == null || input.trim().isEmpty()) {
                return "";
            }

            // 1. Replace spaces and underscores with hyphens
            var slug = SEPARATORS.matcher(input).replaceAll("-");

            // 2. Normalize to separate accents from letters (e.g., "é" becomes "e" + "´")
            slug = Normalizer.normalize(slug, Normalizer.Form.NFD);

            // 3. Remove all non-Latin/non-alphanumeric characters except hyphens
            slug = NON_LATIN.matcher(slug).replaceAll("");

            // 4. Convert to lowercase in a locale-safe way
            slug = slug.toLowerCase(Locale.ENGLISH);

            // 5. Remove duplicate hyphens (e.g., "foo---bar" -> "foo-bar")
            slug = MULTI_HYPHEN.matcher(slug).replaceAll("-");

            // 6. Remove leading and trailing hyphens
            slug = EDGES_HYPHEN.matcher(slug).replaceAll("");

            return slug;
        }
    }
}

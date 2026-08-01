package com.seek_with_sight.seed;

import com.seek_with_sight.order.domain.model.OrderStatus;
import com.seek_with_sight.order.domain.model.PaymentStatus;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.entity.OrderItemEntity;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.repository.OrderJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductVariantEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderSeedService {

    private final OrderJpaRepository orderRepository;
    private final ProductJpaRepository productRepository;
    private final UserJpaRepository userRepository;

    private final Faker faker = new Faker(Locale.US);

    /**
     * Seeds mock orders into the database.
     * * @param orderCount Number of orders to generate (e.g., 100)
     */
    @Transactional
    public void seedOrders(int orderCount) {
        if (orderRepository.count() >= orderCount) {
            log.info("Orders already seeded. Skipping generation.");
            return;
        }

        List<UserEntity> users = userRepository.findAll();
        List<ProductEntity> products = productRepository.findAll();

        if (users.isEmpty()) {
            log.warn("No users found! Please seed users before generating orders.");
            return;
        }

        if (products.isEmpty()) {
            log.warn("No products found! Please run ProductSeedService first.");
            return;
        }

        log.info("Starting generation of {} mock orders...", orderCount);
        List<OrderEntity> ordersToSave = new ArrayList<>();

        for (int i = 0; i < orderCount; i++) {
            OrderEntity order = createRandomOrder(users, products);
            ordersToSave.add(order);

            // Save in batches of 50 to optimize memory usage
            if (ordersToSave.size() % 50 == 0) {
                orderRepository.saveAll(ordersToSave);
                ordersToSave.clear();
                log.info("Saved {} orders...", (i + 1));
            }
        }

        // Save remaining orders
        if (!ordersToSave.isEmpty()) {
            orderRepository.saveAll(ordersToSave);
        }

        log.info("Successfully generated {} orders!", orderCount);
    }

    private OrderEntity createRandomOrder(List<UserEntity> users, List<ProductEntity> products) {
        OrderEntity order = new OrderEntity();

        // 1. Generate Order Number & Assign User
        order.setOrderNumber("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        UserEntity randomUser = users.get(faker.number().numberBetween(0, users.size()));
        order.setUser(randomUser);

        // 2. Assign Realistic Order and Payment Statuses
        OrderStatus status = faker.options().option(OrderStatus.class);
        order.setStatus(status);
        order.setPaymentStatus(determinePaymentStatus(status));

        // 3. Generate 1 to 5 Order Items
        int itemTypesCount = faker.number().numberBetween(1, 6);
        List<OrderItemEntity> items = new ArrayList<>();
        BigDecimal orderTotal = BigDecimal.ZERO;

        for (int i = 0; i < itemTypesCount; i++) {
            // Pick a random product that has variants
            ProductEntity randomProduct = products.get(faker.number().numberBetween(0, products.size()));

            if (randomProduct.getVariants().isEmpty()) {
                continue;
            }

            // Pick a random variant from that product
            List<ProductVariantEntity> variants = randomProduct.getVariants();
            ProductVariantEntity randomVariant = variants.get(faker.number().numberBetween(0, variants.size()));

            // Determine unit price (prefer sale price if available)
            BigDecimal unitPrice = (randomVariant.getSalePrice() != null)
                    ? randomVariant.getSalePrice()
                    : randomVariant.getPrice();

            int quantity = faker.number().numberBetween(1, 4);
            BigDecimal itemTotal = unitPrice.multiply(BigDecimal.valueOf(quantity))
                    .setScale(4, RoundingMode.HALF_UP);

            // Construct OrderItemEntity
            OrderItemEntity item = new OrderItemEntity();
            item.setOrder(order);
            item.setProduct(randomProduct);
            item.setVariant(randomVariant);
            item.setQuantity(quantity);
            item.setCurrencyCode("USD");
            item.setUnitPrice(unitPrice);
            item.setTotalPrice(itemTotal);

            items.add(item);
            orderTotal = orderTotal.add(itemTotal);
        }

        order.setItems(items);
        order.setTotalAmount(orderTotal.setScale(4, RoundingMode.HALF_UP));

        return order;
    }

    /**
     * Maps OrderStatus logically to PaymentStatus.
     */
    private PaymentStatus determinePaymentStatus(OrderStatus orderStatus) {
        return switch (orderStatus) {
            case DELIVERED, SHIPPED, PROCESSING -> PaymentStatus.PAID;
            case REFUNDED -> PaymentStatus.REFUNDED;
            case CANCELLED -> faker.options()
                    .option(PaymentStatus.FAILED, PaymentStatus.PENDING, PaymentStatus.REFUNDED);
            case PENDING -> PaymentStatus.PENDING;
        };
    }
}
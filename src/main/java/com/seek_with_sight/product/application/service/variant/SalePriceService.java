package com.seek_with_sight.product.application.service.variant;

import com.seek_with_sight.product.domain.exception.SaleDatesException;
import com.seek_with_sight.product.domain.model.ProductVariant;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class SalePriceService {
    public void updateSalePrice(
            ProductVariant variant,
            BigDecimal salePrice,
            Instant saleStartDate,
            Instant saleEndDate
    ) {
        if (saleStartDate != null && saleEndDate != null) {
            verifySaleDates(saleStartDate, saleEndDate, variant.getId());

            variant.setSaleStartDate(saleStartDate);
            variant.setSaleEndDate(saleEndDate);
        }

        if (salePrice != null && salePrice.compareTo(variant.getPrice()) < 0) {
            variant.setSalePrice(salePrice);
        }
    }

    private void verifySaleDates(Instant saleStartDate, Instant saleEndDate, UUID variantId) {
        if (!saleStartDate.isBefore(saleEndDate)) {
            throw new SaleDatesException(saleStartDate, saleEndDate, variantId);
        }
    }
}

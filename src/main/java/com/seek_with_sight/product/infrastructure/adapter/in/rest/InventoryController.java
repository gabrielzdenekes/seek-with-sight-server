package com.seek_with_sight.product.infrastructure.adapter.in.rest;

import com.seek_with_sight.product.application.port.in.inventory.UpdateProductInventoryUseCase;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.inventory.UpdateInventoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final UpdateProductInventoryUseCase updateProductInventoryUseCase;

    @PutMapping("/{variantId}")
    public void updateInventory(
            @Valid @RequestBody UpdateInventoryRequest request,
            @PathVariable UUID variantId
    ) {
        updateProductInventoryUseCase.update(variantId, request.quantity());
    }
}

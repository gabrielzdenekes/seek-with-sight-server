package com.seek_with_sight.order.infrastructure.adapter.out.persistence;

import com.seek_with_sight.order.application.port.out.OrderRepositoryPort;
import com.seek_with_sight.order.domain.model.Order;
import com.seek_with_sight.product.domain.model.product.BestSellingVariantItem;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.projection.BestSellingVariantProjection;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.mapper.OrderPersistenceMapper;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.repository.OrderJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public class OrderPersistenceAdapter
        extends BasePersistenceAdapter<Order, OrderEntity, OrderJpaRepository, OrderPersistenceMapper>
        implements OrderRepositoryPort {

    public OrderPersistenceAdapter(OrderJpaRepository repository, OrderPersistenceMapper mapper) {
        super(repository, mapper, OrderEntity::new);
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        return repository.findById(orderId).map(o -> mapper.toDomain(o, new CycleAvoidingMappingContext()));
    }

    @Override
    public Page<BestSellingVariantItem> findBestSellingVariants(Pageable pageable) {
        return repository.findBestSellingVariants(pageable)
                .map(p ->
                        mapper.toBestSellingVariantItem(p, new CycleAvoidingMappingContext())
                );
    }
}

package com.seek_with_sight.cart.infrastructure.adapter.out.persistence;

import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.entity.CartEntity;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.mapper.CartPersistenceMapper;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.repository.CartJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;

import java.util.Optional;
import java.util.UUID;

public class CartPersistenceAdapter
        extends BasePersistenceAdapter<Cart, CartEntity, CartJpaRepository>
        implements CartRepositoryPort {
    private CartPersistenceMapper mapper;

    public CartPersistenceAdapter(CartJpaRepository repository, CartPersistenceMapper mapper) {
        super(repository, mapper, CartEntity::new);
        this.mapper = mapper;
    }

    @Override
    public Optional<Cart> findByUserId(UUID userId) {
        return repository.findByUserId(userId).map(mapper::toDomain);
    }

    @Override
    public Optional<Cart> findWithItemsByUserId(UUID userId) {
        return repository.findWithItemsByUserId(userId).map(mapper::toDomainWithDetails);
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return repository.existsByUserId(userId);
    }
}

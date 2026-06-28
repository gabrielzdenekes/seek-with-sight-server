package com.seek_with_sight.cart.infrastructure.adapter.out.persistence;

import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.entity.CartEntity;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.mapper.CartPersistenceMapper;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.repository.CartJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.config.cache.CacheNames;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;
import java.util.UUID;

public class CartPersistenceAdapter
        extends BasePersistenceAdapter<Cart, CartEntity, CartJpaRepository, CartPersistenceMapper>
        implements CartRepositoryPort {

    public CartPersistenceAdapter(CartJpaRepository repository, CartPersistenceMapper mapper) {
        super(repository, mapper, CartEntity::new);
    }

    @Override
    @CachePut(
            cacheNames = CacheNames.CART,
            key = "#cart.user.id"
    )
    public Cart update(Cart cart) {
        return super.update(cart);
    }

    @Override
    @Cacheable(
            cacheNames = CacheNames.CART,
            key = "#userId"
    )
    public Optional<Cart> findWithItemsByUserId(UUID userId) {
        return repository.findWithItemsByUserId(userId).map(mapper::toDomainWithDetails);
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return repository.existsByUserId(userId);
    }
}

package com.seek_with_sight.shared.application.port.out;

public interface BaseRepositoryPort<D> {
    D create(D domain);

    D update(D domain);
}

package com.seek_with_sight.shared.application.port.out.event;

public interface DomainEventPublisher {
    void publish(Object event);
}

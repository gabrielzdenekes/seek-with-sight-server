package com.seek_with_sight.shared.application.port.out.event;

import com.seek_with_sight.shared.domain.event.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}

package com.seek_with_sight.shared.domain.event;

import java.time.Instant;
import java.util.UUID;

public class DomainEvent {
    private final UUID eventId;
    private final Instant occurredAt;

    public DomainEvent() {
        eventId = UUID.randomUUID();
        occurredAt = Instant.now();
    }

    public UUID getEventId() {
        return eventId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}

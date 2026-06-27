package com.seek_with_sight.shared.infrastructure.config.event;

import com.seek_with_sight.shared.application.port.out.event.DomainEventPublisher;
import com.seek_with_sight.shared.infrastructure.adapter.out.event.SpringDomainEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventsConfig {
    @Bean
    public DomainEventPublisher domainEventPublisher(
            ApplicationEventPublisher publisher
    ) {
        return new SpringDomainEventPublisher(publisher);
    }
}

package com.example.port.out;

import com.example.event.DomainEvent;

public interface DomainEventRepository {
    void save(DomainEvent event);
}

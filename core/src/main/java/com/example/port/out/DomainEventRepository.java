package com.example.port.out;

import com.example.event.DomainEvent;

public interface DomainEventRepository {
    boolean save(DomainEvent event);
}

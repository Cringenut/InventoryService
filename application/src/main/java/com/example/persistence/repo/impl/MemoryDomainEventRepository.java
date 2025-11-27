package com.example.persistence.repo.impl;

import com.example.event.DomainEvent;
import com.example.persistence.entity.DomainEventEntity;
import com.example.persistence.repo.SpringDataDomainEventJpaRepository;
import com.example.port.out.DomainEventRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryDomainEventRepository implements DomainEventRepository {

    private final SpringDataDomainEventJpaRepository repo;

    public MemoryDomainEventRepository(SpringDataDomainEventJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(DomainEvent event) {
        DomainEventEntity entity = new DomainEventEntity();
        entity.setSku(event.getSku());
        entity.setType(event.getType());
        entity.setCreatedAt(event.getCreatedAt());
        entity.setPayload(event.getPayload());
        repo.save(entity);
    }
}
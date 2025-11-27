package com.example.persistence.repo;

import com.example.persistence.entity.DomainEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDomainEventJpaRepository
        extends JpaRepository<DomainEventEntity, String> {
}

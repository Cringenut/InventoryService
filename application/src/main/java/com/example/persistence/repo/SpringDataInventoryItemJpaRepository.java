package com.example.persistence.repo;

import com.example.persistence.entity.InventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataInventoryItemJpaRepository
        extends JpaRepository<InventoryItemEntity, String> {
}

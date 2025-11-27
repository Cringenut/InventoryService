package com.example.persistence.repo.impl;

import com.example.model.InventoryItem;
import com.example.persistence.entity.InventoryItemEntity;
import com.example.persistence.repo.SpringDataInventoryItemJpaRepository;
import com.example.port.out.InventoryItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemoryInventoryItemRepository implements InventoryItemRepository {
    private final SpringDataInventoryItemJpaRepository jpaRepository;

    public MemoryInventoryItemRepository(SpringDataInventoryItemJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<InventoryItem> findBySku(String sku) {
        return jpaRepository.findById(sku)
                .map(this::toDomain);
    }

    @Override
    @Transactional
    public boolean save(InventoryItem item, long expectedVersion) {
        int updated = jpaRepository.updateInventory(
                item.sku(),
                item.available(),
                item.reserved(),
                item.version(),
                expectedVersion
        );
        return updated == 1;
    }

    private InventoryItem toDomain(InventoryItemEntity e) {
        return new InventoryItem(
                e.getSku(),
                e.getAvailable(),
                e.getReserved(),
                e.getVersion()
        );
    }

    private InventoryItemEntity toEntity(InventoryItem item) {
        return new InventoryItemEntity(
                item.sku(),
                item.available(),
                item.reserved(),
                item.version()
        );
    }
}

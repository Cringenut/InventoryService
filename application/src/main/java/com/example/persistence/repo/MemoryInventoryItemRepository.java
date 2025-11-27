package com.example.persistence.repo;

import com.example.model.InventoryItem;
import com.example.port.out.InventoryItemRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemoryInventoryItemRepository implements InventoryItemRepository {
    @Override
    public Optional<InventoryItem> findBySku(String sku) {
        return Optional.empty();
    }

    @Override
    public boolean save(InventoryItem item) {
        return false;
    }
}

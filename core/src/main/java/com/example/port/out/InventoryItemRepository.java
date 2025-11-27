package com.example.port.out;

import com.example.model.InventoryItem;

import java.util.Optional;

public interface InventoryItemRepository {
    Optional<InventoryItem> findBySku(String sku);
    boolean save(InventoryItem item, long expectedVersion);
}

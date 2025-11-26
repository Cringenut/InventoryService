package com.example.port.out;

public interface InventoryItemRepository {
    void reserve(String sku, int quantity);
}

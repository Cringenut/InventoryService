package com.example.service;

import com.example.port.out.InventoryItemRepository;

public class InventoryItemService implements InventoryItemRepository {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public void reserve(String sku, int quantity) {

    }
}

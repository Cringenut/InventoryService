package com.example.service;

import com.example.model.InventoryItem;
import com.example.port.in.ReserveItemUseCase;
import com.example.port.out.InventoryItemRepository;

public class InventoryItemService implements ReserveItemUseCase {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public void reserve(String sku, int qty) {
        InventoryItem currentItem = inventoryItemRepository.findBySku(sku).orElse(null);
        InventoryItem changedItem = currentItem.reserve(qty);
       inventoryItemRepository.save(changedItem);
    }
}

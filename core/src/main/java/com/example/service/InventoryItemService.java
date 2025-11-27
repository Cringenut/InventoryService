package com.example.service;

import com.example.event.impl.ItemReservedEvent;
import com.example.exception.ConcurrencyException;
import com.example.model.InventoryItem;
import com.example.port.in.ReserveItemUseCase;
import com.example.port.out.DomainEventRepository;
import com.example.port.out.InventoryItemRepository;

public class InventoryItemService implements ReserveItemUseCase {

    private final InventoryItemRepository inventoryItemRepository;
    private final DomainEventRepository domainEventRepository;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository, DomainEventRepository domainEventRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.domainEventRepository = domainEventRepository;
    }

    @Override
    public void reserve(String sku, int qty) {
        for (int attempt = 0; attempt < 3; attempt++) {

            InventoryItem currentItem = inventoryItemRepository.findBySku(sku)
                    .orElseThrow(() -> new RuntimeException("InventoryItem not found"));

            long expectedVersion = currentItem.version();
            InventoryItem changedItem = currentItem.reserve(qty);

            boolean updated = inventoryItemRepository.save(changedItem, expectedVersion);

            if (!updated) {
                continue; // retry
            }

            ItemReservedEvent event = new ItemReservedEvent(
                    changedItem.sku(),
                    "{ \"qty\": %d }".formatted(qty)
            );

            domainEventRepository.save(event);
            return;
        }

        throw new ConcurrencyException("Failed to reserve item");
    }
}

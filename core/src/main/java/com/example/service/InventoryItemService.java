package com.example.service;

import com.example.event.impl.ItemReservedEvent;
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
        InventoryItem currentItem = inventoryItemRepository.findBySku(sku).orElse(null);
        InventoryItem changedItem = currentItem.reserve(qty);
        boolean success = inventoryItemRepository.save(changedItem);

        if (!success)
            throw new RuntimeException("Failed to reserve item");

        ItemReservedEvent event = new ItemReservedEvent(
                changedItem.sku(),
                "{ \"qty\": %d }".formatted(qty)
        );

        success = domainEventRepository.save(event);

        if (!success)
            throw new RuntimeException("Failed to reserve item");

    }
}

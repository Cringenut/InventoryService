package com.test_task.inventory_service.core.entity;

public record InventoryItem(String sku, int available, int reserved, Long version) {
}

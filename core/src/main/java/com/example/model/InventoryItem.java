package com.example.model;

public record InventoryItem(
        String sku,
        int available,
        int reserved,
        long version
) {

}

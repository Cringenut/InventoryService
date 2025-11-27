package com.example.model;

public record InventoryItem(
        String sku,
        int available,
        int reserved,
        long version
) {
    public InventoryItem reserve(int qty) {
        if (qty <= 0) throw new IllegalArgumentException();
        if (available < qty) throw new IllegalArgumentException();

        return new InventoryItem(
                sku,
                available - qty,
                reserved + qty,
                version + 1
        );
    }
}

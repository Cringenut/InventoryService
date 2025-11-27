package com.example.event.impl;

import com.example.event.DomainEvent;
import java.time.Instant;

public class ItemReservedEvent implements DomainEvent {

    private final String sku;
    private final String payload;
    private final Instant createdAt = Instant.now();

    public ItemReservedEvent(String sku, String payload) {
        this.sku = sku;
        this.payload = payload;
    }

    @Override
    public String getSku() {
        return this.sku;
    }

    @Override
    public String getPayload() {
        return this.payload;
    }

    @Override
    public String getType() {
        return "ItemReserved";
    }

    @Override
    public Instant getCreatedAt() {
        return this.createdAt;
    }
}

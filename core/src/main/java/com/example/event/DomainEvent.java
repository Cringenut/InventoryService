package com.example.event;

import java.time.Instant;

public interface DomainEvent {

    String getSku();
    String getPayload();
    String getType();
    Instant getCreatedAt();

}

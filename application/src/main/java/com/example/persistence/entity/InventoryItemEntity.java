package com.example.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "inventory_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryItemEntity {

    @Id
    private String sku;
    private int available;
    private int reserved;
    private long version;

}

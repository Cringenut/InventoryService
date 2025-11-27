package com.example.persistence.repo;

import com.example.persistence.entity.InventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SpringDataInventoryItemJpaRepository
        extends JpaRepository<InventoryItemEntity, String> {

    @Modifying
    @Query("""
        UPDATE InventoryItemEntity i
           SET i.available = :available,
               i.reserved  = :reserved,
               i.version   = :newVersion
         WHERE i.sku       = :sku
           AND i.version   = :expectedVersion
        """)
    int updateInventory(String sku,
                        int available,
                        int reserved,
                        long newVersion,
                        long expectedVersion);
}

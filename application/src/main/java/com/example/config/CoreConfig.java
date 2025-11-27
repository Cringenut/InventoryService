package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.port.in.ReserveItemUseCase;
import com.example.port.out.InventoryItemRepository;
import com.example.service.InventoryItemService;

@Configuration
public class CoreConfig {

    @Bean
    public ReserveItemUseCase reserveItemUseCase(InventoryItemRepository repository) {
        return new InventoryItemService(repository);
    }
}

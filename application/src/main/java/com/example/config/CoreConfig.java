package com.example.config;

import com.example.port.out.DomainEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.port.in.ReserveItemUseCase;
import com.example.port.out.InventoryItemRepository;
import com.example.service.InventoryItemService;

// spring free bean injector
@Configuration
public class CoreConfig {

    @Bean
    public ReserveItemUseCase reserveItemUseCase(InventoryItemRepository repository,
                                                 DomainEventRepository eventRepository) {
        return new InventoryItemService(repository, eventRepository);
    }
}

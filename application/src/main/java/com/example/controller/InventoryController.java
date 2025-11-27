package com.example.controller;

import com.example.port.in.ReserveItemUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final ReserveItemUseCase reserveItemUseCase;

    public InventoryController(ReserveItemUseCase reserveItemUseCase) {
        this.reserveItemUseCase = reserveItemUseCase;
    }

    @PostMapping("/{sku}/reserve")
    public void reserve(@PathVariable String sku) {

    }

}

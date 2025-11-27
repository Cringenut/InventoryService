package com.example.controller;

import com.example.dto.ReserveRequestDto;
import com.example.port.in.ReserveItemUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final ReserveItemUseCase reserveItemUseCase;

    public InventoryController(ReserveItemUseCase reserveItemUseCase) {
        this.reserveItemUseCase = reserveItemUseCase;
    }

    @PostMapping("/{sku}/reserve")
    public void reserve(@PathVariable String sku, @RequestBody ReserveRequestDto requestDto) {
        reserveItemUseCase.reserve(sku, requestDto.getQty());
    }

}

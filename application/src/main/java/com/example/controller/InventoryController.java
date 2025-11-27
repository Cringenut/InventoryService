package com.example.controller;

import com.example.dto.ReserveRequestDto;
import com.example.exception.ConcurrencyException;
import com.example.port.in.ReserveItemUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final ReserveItemUseCase reserveItemUseCase;

    public InventoryController(ReserveItemUseCase reserveItemUseCase) {
        this.reserveItemUseCase = reserveItemUseCase;
    }

    @PostMapping("/{sku}/reserve")
    public ResponseEntity<?> reserve(
            @PathVariable String sku,
            @RequestBody ReserveRequestDto requestDto) {

        try {
            reserveItemUseCase.reserve(sku, requestDto.getQty());
            return ResponseEntity.ok().build();
        } catch (ConcurrencyException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

}

package com.pawelsuc.skBackend.controller;

import com.pawelsuc.skBackend.entity.Warehouse;
import com.pawelsuc.skBackend.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseRepository warehouseRepository;


    @PostMapping("/warehouses")
    Warehouse newWarehouse (@RequestBody Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @GetMapping("/warehouses")
    List<Warehouse> listwarehouses() {
        return warehouseRepository.findAll();

    }
    @DeleteMapping("/warehouses")
    ResponseEntity deleteWarehouse(@RequestBody Long idWarehouse) {
        warehouseRepository.deleteById(idWarehouse);
        return ResponseEntity.ok().build();
    }
}

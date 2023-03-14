package com.pawelsuc.skBackend.controller;

import com.pawelsuc.skBackend.entity.QuantityType;
import com.pawelsuc.skBackend.repository.QuantityTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuantityTypeController {
    private final QuantityTypeRepository quantityTypeRepository;


    @PostMapping("/quantity_types")
    QuantityType newQuantityType (@RequestBody QuantityType quantityType) {
        return quantityTypeRepository.save(quantityType);
    }

    @GetMapping("/quantity_types")
    List<QuantityType> listQuantityTypes() {
        return quantityTypeRepository.findAll();

    }
    @DeleteMapping("/quantity_types")
    ResponseEntity deleteQuantityType(@RequestBody Long idQuantityType) {
        quantityTypeRepository.deleteById(idQuantityType);
        return ResponseEntity.ok().build();
    }
}

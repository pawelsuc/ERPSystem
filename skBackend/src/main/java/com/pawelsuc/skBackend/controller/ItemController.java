package com.pawelsuc.skBackend.controller;

import com.pawelsuc.skBackend.entity.Item;
import com.pawelsuc.skBackend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;


    @PostMapping("/items")
    public Item newItem (@RequestBody Item newItem) {
        return itemRepository.save(newItem);
    }

    @GetMapping("/items")
    public List<Item> listItems() {
        return itemRepository.findAll();

    }
    @DeleteMapping("/items")
    public ResponseEntity deleteItem(@RequestBody Long idItem) {
        itemRepository.deleteById(idItem);
        return ResponseEntity.ok().build();
    }
}

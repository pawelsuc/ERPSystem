package com.example.skjavafx.rest;

import com.example.skjavafx.dto.ItemDto;
import com.example.skjavafx.dto.ItemSaveDto;
import com.example.skjavafx.handler.ProcessFinishedHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ItemRestClient {

    private static final String ITEMS_URL = "http://localhost:8080/items";
    private final RestTemplate restTemplate;


    public ItemRestClient() {
        restTemplate = new RestTemplate();
    }

    public List<ItemDto> getItems() {
        ResponseEntity<ItemDto[]> itemResponseEntity = restTemplate.getForEntity(ITEMS_URL, ItemDto[].class);
        return Arrays.asList(itemResponseEntity.getBody());
    }

    public void saveItem(ItemSaveDto dto, ProcessFinishedHandler processFinishedHandler) {
    }
}
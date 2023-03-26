package com.pawelsuc.skBackend.service;

import com.pawelsuc.skBackend.dto.ItemSaveDto;
import com.pawelsuc.skBackend.entity.Item;
import com.pawelsuc.skBackend.entity.QuantityType;
import com.pawelsuc.skBackend.entity.Warehouse;
import com.pawelsuc.skBackend.repository.ItemRepository;
import com.pawelsuc.skBackend.repository.QuantityTypeRepository;
import com.pawelsuc.skBackend.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;
    private final QuantityTypeRepository quantityTypeRepository;

    public Item saveItem(ItemSaveDto dto){
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(dto.getIdWarehouse());
        Optional<QuantityType> quantityTypeOptional = quantityTypeRepository.findById(dto.getIdQuantityType());
        if(!warehouseOptional.isPresent() || !quantityTypeOptional.isPresent()) {
            throw new RuntimeException("Incorrect indentifiers: id:warehouse: " + dto.getIdWarehouse() + ",  " +
                    "idQuantityType" + dto.getIdQuantityType());
        }
        Warehouse warehouse = warehouseOptional.get();
        QuantityType quantityType = quantityTypeOptional.get();
        Item item = Item.of(dto);
        item.setQuantityType(quantityType);
        item.setWarehouse(warehouse);
        return itemRepository.save(item);
    }

}

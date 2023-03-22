package com.pawelsuc.skBackend.service;


import com.pawelsuc.skBackend.dto.ItemDto;
import com.pawelsuc.skBackend.dto.WarehouseDto;
import com.pawelsuc.skBackend.dto.WarehouseModuleDto;
import com.pawelsuc.skBackend.entity.Warehouse;
import com.pawelsuc.skBackend.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseModuleDto getWarehouseModuleData() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        List<WarehouseDto> warehouseDtoList = warehouseList.stream()
                .map(WarehouseDto::of).collect(Collectors.toList());
        List<ItemDto> itemDtoList = warehouseList.get(0).getItems().stream()
                .map(ItemDto::of).collect(Collectors.toList());
        WarehouseDto selectedWarehouseDto = WarehouseDto.of(warehouseList.get(0));
        WarehouseModuleDto warehouseModuleDto = new WarehouseModuleDto(selectedWarehouseDto, warehouseDtoList,
                itemDtoList);
        return warehouseModuleDto;
    }

    public WarehouseModuleDto getWarehouseModuleData(Long idWarehouse) {
        List<Warehouse> warehouseList = warehouseRepository.findAll();

        List<WarehouseDto> warehouseDtoList = warehouseList.stream()
                .map(WarehouseDto::of).collect(Collectors.toList());
        Optional<Warehouse> optionalWarehouse =
                warehouseList.stream().filter(x -> idWarehouse.equals(x.getIdWarehouse())).findFirst();
        Warehouse selectedWarehouse = optionalWarehouse.get();

        List<ItemDto> itemDtoList = selectedWarehouse.getItems().stream()
                .map(ItemDto::of).collect(Collectors.toList());

        WarehouseModuleDto warehouseModuleDto = new WarehouseModuleDto(WarehouseDto.of(selectedWarehouse), warehouseDtoList,
                itemDtoList);
        return warehouseModuleDto;
    }


}

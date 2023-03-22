package com.example.skjavafx.dto;


import lombok.Data;

import java.util.List;

@Data
public class WarehouseModuleDto {
    private WareHouseDto selectedWarehouse;
    private List<WareHouseDto> wareHouseDtoList;
    private List<ItemDto> itemList;
}

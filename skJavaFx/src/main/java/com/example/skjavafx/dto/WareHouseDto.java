package com.example.skjavafx.dto;


import lombok.Data;

@Data
public class WareHouseDto {
    private Long idWarehouse;
    private String name;

    @Override
    public String toString(){
        return name;
    }
}

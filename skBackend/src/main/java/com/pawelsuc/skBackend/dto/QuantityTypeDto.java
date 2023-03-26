package com.pawelsuc.skBackend.dto;

import com.pawelsuc.skBackend.entity.QuantityType;
import lombok.Data;

@Data
public class QuantityTypeDto {
    private Long idQuantityType;
    private String name;

    public static QuantityTypeDto of (QuantityType quantityType) {
        QuantityTypeDto dto = new QuantityTypeDto();
        dto.setName(quantityType.getName());
        dto.setIdQuantityType(quantityType.getIdQuantityType());
        return dto;
    }
}

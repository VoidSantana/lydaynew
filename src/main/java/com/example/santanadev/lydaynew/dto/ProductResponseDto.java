package com.example.santanadev.lydaynew.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {

    private Long id;
    private String name;
    private String sku;
    private String description;
}

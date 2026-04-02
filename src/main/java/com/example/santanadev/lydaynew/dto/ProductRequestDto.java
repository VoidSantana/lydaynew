package com.example.santanadev.lydaynew.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String sku;

    private String description;
}

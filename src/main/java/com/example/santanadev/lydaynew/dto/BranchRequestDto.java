package com.example.santanadev.lydaynew.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BranchRequestDto {

    @NotBlank
    private String name;

    private String code;
}

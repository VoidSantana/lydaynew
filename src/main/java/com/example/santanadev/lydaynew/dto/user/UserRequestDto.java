package com.example.santanadev.lydaynew.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

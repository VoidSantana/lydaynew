package com.example.santanadev.lydaynew.dto.user;

import com.example.santanadev.lydaynew.entity.user.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class UserResponseDto {

    private Long id;
    private String username;
    private Set<Role> roles;
    private LocalDateTime createdAt;
}

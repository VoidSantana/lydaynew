package com.example.santanadev.lydaynew.security.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
    private Long branchId;
}

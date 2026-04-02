package com.example.santanadev.lydaynew.util;

import com.example.santanadev.lydaynew.security.user.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static UserDetailsImpl getCurrentUser() {
        return (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public static Long getCurrentBranchId() {
        return getCurrentUser().getBranchId();
    }
}

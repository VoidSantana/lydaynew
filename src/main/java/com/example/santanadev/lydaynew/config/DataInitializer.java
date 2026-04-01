package com.example.santanadev.lydaynew.config;

import com.example.santanadev.lydaynew.entity.user.Role;
import com.example.santanadev.lydaynew.entity.user.User;
import com.example.santanadev.lydaynew.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){

        if (userRepository.count() == 0){

            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .roles(Set.of(Role.ROLE_ADMIN))
                    .build();
            userRepository.save(admin);

            System.out.println(">>>USUÁRIO ADMIN CRIADO<<<");
        }
    }
}

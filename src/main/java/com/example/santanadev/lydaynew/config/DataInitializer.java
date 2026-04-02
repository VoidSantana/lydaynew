package com.example.santanadev.lydaynew.config;

import com.example.santanadev.lydaynew.entity.Branch;
import com.example.santanadev.lydaynew.entity.Role;
import com.example.santanadev.lydaynew.entity.User;
import com.example.santanadev.lydaynew.repository.BranchRepository;
import com.example.santanadev.lydaynew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){

        if (branchRepository.count() == 0){
            Branch branch = Branch.builder()
                    .name("Matriz")
                    .code("MTZ")
                    .build();
            branchRepository.save(branch);
        }
        Branch branch = branchRepository.findAll().get(0);

        if (userRepository.findByUsername("admin").isEmpty()){
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .roles(Set.of(Role.ROLE_ADMIN))
                    .branch(branch)
                    .build();
            userRepository.save(admin);
            System.out.println(">>>USUÁRIO ADMIN CRIADO<<<");
        }

    }
}

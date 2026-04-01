package com.example.santanadev.lydaynew.repository;

import com.example.santanadev.lydaynew.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByUsername(String username);

    boolean existsByUsername(String username);

}

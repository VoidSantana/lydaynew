package com.example.santanadev.lydaynew.repository;

import com.example.santanadev.lydaynew.entity.User;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByUsername(String username);

    List<User> findByBranchId(Long branchId);

    boolean existsByUsername(String username);

}

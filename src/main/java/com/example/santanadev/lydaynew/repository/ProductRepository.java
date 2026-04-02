package com.example.santanadev.lydaynew.repository;

import com.example.santanadev.lydaynew.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBranchId(Long branchId);

    boolean existsBySku(String sku);

}

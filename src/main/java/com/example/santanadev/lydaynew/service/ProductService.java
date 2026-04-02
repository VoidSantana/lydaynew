package com.example.santanadev.lydaynew.service;

import com.example.santanadev.lydaynew.dto.ProductRequestDto;
import com.example.santanadev.lydaynew.dto.ProductResponseDto;
import com.example.santanadev.lydaynew.entity.Branch;
import com.example.santanadev.lydaynew.entity.Product;
import com.example.santanadev.lydaynew.exeption.BusinessException;
import com.example.santanadev.lydaynew.repository.BranchRepository;
import com.example.santanadev.lydaynew.repository.ProductRepository;
import com.example.santanadev.lydaynew.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final BranchRepository branchRepository;

    public ProductResponseDto create(ProductRequestDto dto) {
        if (productRepository.existsBySku(dto.getSku())) {
            throw new BusinessException("SKU Já Existe. ");
        }
        Product product = Product.builder()
                .name(dto.getName())
                .sku(dto.getSku())
                .description(dto.getDescription())
                .branch(null)
                .build();

        Long branchId = SecurityUtil.getCurrentBranchId();
        Branch branch = branchRepository.findById(branchId)
                        .orElseThrow(() -> new BusinessException("Filial não encontrada"));
        product.setBranch(branch);

        return mapToDTO(productRepository.save(product));
    }

    public List<ProductResponseDto> findAll(){

        Long branchId = SecurityUtil.getCurrentBranchId();

        return productRepository.findByBranchId(branchId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ProductResponseDto mapToDTO(Product product){

        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .build();
    }
}

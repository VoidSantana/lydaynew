package com.example.santanadev.lydaynew.controller;

import com.example.santanadev.lydaynew.dto.ProductRequestDto;
import com.example.santanadev.lydaynew.dto.ProductResponseDto;
import com.example.santanadev.lydaynew.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody @Valid ProductRequestDto dto){
        return ResponseEntity.ok(productService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
    // TODO criar um findById e tambem colocar paginação completa, delete e update.
    // TODO não esquecer tambem de refatorar a arquitetura geral,
    //  para reduzir a dificuldade de encontrar os endpoints

}

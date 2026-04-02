package com.example.santanadev.lydaynew.controller;

import com.example.santanadev.lydaynew.dto.BranchRequestDto;
import com.example.santanadev.lydaynew.dto.BranchResponseDto;
import com.example.santanadev.lydaynew.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchResponseDto> create(
            @RequestBody @Valid BranchRequestDto dto){
        return ResponseEntity.ok(branchService.create(dto));
    }

    @GetMapping
    public ResponseEntity<Page<BranchResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(branchService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(branchService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BranchResponseDto> update(
            @PathVariable Long id,
            @RequestBody @Valid BranchRequestDto dto
    ){

        return ResponseEntity.ok(branchService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        branchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

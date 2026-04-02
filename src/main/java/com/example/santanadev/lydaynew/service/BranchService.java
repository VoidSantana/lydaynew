package com.example.santanadev.lydaynew.service;

import com.example.santanadev.lydaynew.dto.BranchRequestDto;
import com.example.santanadev.lydaynew.dto.BranchResponseDto;
import com.example.santanadev.lydaynew.entity.Branch;
import com.example.santanadev.lydaynew.exeption.BusinessException;
import com.example.santanadev.lydaynew.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchResponseDto create(BranchRequestDto dto){

        Branch branch = Branch.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .build();
        return mapToDTO(branchRepository.save(branch));
    }

    public Page<BranchResponseDto> findAll(int page, int size){
        return branchRepository.findAll(PageRequest.of(page, size))
                .map(this::mapToDTO);
    }

    public BranchResponseDto findById(Long id){
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Filial Não Encontrada"));
        return mapToDTO(branch);
    }

    public BranchResponseDto update(Long id, BranchRequestDto dto){
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Filial não encontrada para atualização"));
        branch.setName(dto.getName());
        branch.setCode(dto.getCode());

        return mapToDTO(branchRepository.save(branch));
    }

    public void delete(Long id){
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Filial não encontrada para deleção"));
        branch.setDeleted(true);
    }

    private BranchResponseDto mapToDTO(Branch branch){
        return BranchResponseDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .code(branch.getCode())
                .build();
    }
}

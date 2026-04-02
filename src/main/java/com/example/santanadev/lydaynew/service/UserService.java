package com.example.santanadev.lydaynew.service;

import com.example.santanadev.lydaynew.dto.UserRequestDto;
import com.example.santanadev.lydaynew.dto.UserResponseDto;
import com.example.santanadev.lydaynew.entity.Branch;
import com.example.santanadev.lydaynew.entity.Role;
import com.example.santanadev.lydaynew.entity.User;
import com.example.santanadev.lydaynew.exeption.BusinessException;
import com.example.santanadev.lydaynew.repository.BranchRepository;
import com.example.santanadev.lydaynew.repository.UserRepository;
import com.example.santanadev.lydaynew.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto create(UserRequestDto dto){

        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new BusinessException("Filial Não Encontrada"));

        if (userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new BusinessException("Usuario Já Existe");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Set.of(Role.ROLE_USER))
                .branch(branch)
                .build();
        return mapToDTO(userRepository.save(user));
    }

    public Page<UserResponseDto> findAll(int page, int size){

        Long branchId = SecurityUtil.getCurrentBranchId();

        Pageable pageable = PageRequest.of(page, size);

        return userRepository.findByBranchId(branchId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        list -> new PageImpl<>(list, pageable, list.size()
                        )
                ));
    }

    public UserResponseDto findById(Long id){

        Long branchId = SecurityUtil.getCurrentBranchId();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuario não encontrado"));

        if (!user.getBranch().getId().equals(branchId)){
            throw new BusinessException("Acesso negado a outra filial");
        }
        return mapToDTO(user);
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuario nao encontrado para atualização"));

        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return mapToDTO(userRepository.save(user));
    }

    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new BusinessException("Usuario não encontrado para deleção"));
        user.setDeleted(true);
        userRepository.save(user);
    }

    private UserResponseDto mapToDTO(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .build();
    }
}

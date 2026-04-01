package com.example.santanadev.lydaynew.service.user;

import com.example.santanadev.lydaynew.dto.user.UserRequestDto;
import com.example.santanadev.lydaynew.dto.user.UserResponseDto;
import com.example.santanadev.lydaynew.entity.user.Role;
import com.example.santanadev.lydaynew.entity.user.User;
import com.example.santanadev.lydaynew.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto create(UserRequestDto dto){

        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .roles(Set.of(Role.ROLE_USER))
                .build();
        return mapToDTO(userRepository.save(user));
    }

    public Page<UserResponseDto> findAll(int page, int size){

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return userRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    public UserResponseDto findById(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        return mapToDTO(user);
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado para atualização"));

        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return mapToDTO(userRepository.save(user));
    }

    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario não encontrado para deleção"));
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

package com.example.santanadev.lydaynew.security.auth;

import com.example.santanadev.lydaynew.entity.Branch;
import com.example.santanadev.lydaynew.entity.Role;
import com.example.santanadev.lydaynew.entity.User;
import com.example.santanadev.lydaynew.exeption.BusinessException;
import com.example.santanadev.lydaynew.repository.BranchRepository;
import com.example.santanadev.lydaynew.repository.UserRepository;
import com.example.santanadev.lydaynew.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BranchRepository branchRepository;

    public AuthResponse register(AuthRequest request){

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new BusinessException("Filial Não Encontrada"));

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(Role.ROLE_USER))
                .branch(branch)
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
    public AuthResponse login(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(request.getUsername());

        return new AuthResponse(token);
    }
}

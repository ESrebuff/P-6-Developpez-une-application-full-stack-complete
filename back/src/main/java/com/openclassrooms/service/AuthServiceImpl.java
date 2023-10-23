package com.openclassrooms.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.jsonWebToken.JwtService;
import com.openclassrooms.mappers.dtos.AuthResponseDto;
import com.openclassrooms.mappers.dtos.LoginRequestDto;
import com.openclassrooms.mappers.dtos.RegisterRequestDto;
import com.openclassrooms.modele.Role;
import com.openclassrooms.modele.User;
import com.openclassrooms.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponseDto.builder()
            .token(token)
            .build();

    }

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .name(request.getName())
            .role(Role.USER)
            .build();

        userRepository.save(user);
        return AuthResponseDto.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}

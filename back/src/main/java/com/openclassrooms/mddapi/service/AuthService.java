package com.openclassrooms.mddapi.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.openclassrooms.mddapi.dto.AuthResponseDto;
import com.openclassrooms.mddapi.dto.LoginRequestDto;
import com.openclassrooms.mddapi.dto.RegisterRequestDto;
import com.openclassrooms.mddapi.dto.UserUpdateDto;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceI {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        
        return AuthResponseDto.builder()
                .token(jwtService.getAccessToken(user))
                .refreshToken(jwtService.getRefreshToken(user))
                .build();

    }

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        if (isValidEmail(request.getUsername())) {
            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .build();

            userRepository.save(user);
            return AuthResponseDto.builder()
                    .token(jwtService.getAccessToken(user))
                    .refreshToken(jwtService.getRefreshToken(user))
                    .build();
        }
        return null;

    }

    @Override
    public AuthResponseDto updateProfile(UserUpdateDto request) {
        // Retrieve the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Retrieve the user from the database based on the authenticated user's
        // username
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the user's information
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        // Save the updated user to the database
        userRepository.save(user);

        // Generate a new JWT token for the updated user
        UserDetails updatedUserDetails = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Updated user not found"));

        // Generate a new access token with the updated email
        String newAccessToken = jwtService.getAccessToken(updatedUserDetails);

        // Generate a new refresh token
        String newRefreshToken = jwtService.getRefreshToken(updatedUserDetails);

        
        return AuthResponseDto.builder()
                .token(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    private boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
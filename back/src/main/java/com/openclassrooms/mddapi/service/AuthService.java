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

/**
 * Service class for managing user authentication and registration.
 */
@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceI {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param request The LoginRequestDto containing user login details.
     * @return AuthResponseDto containing the JWT token.
     */
    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        return AuthResponseDto.builder()
                .jwt(jwtService.getAccessToken(user))
                .build();
    }

    /**
     * Registers a new user based on the provided registration details.
     *
     * @param request The RegisterRequestDto containing user registration details.
     * @return AuthResponseDto containing the JWT token.
     */
    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        if (isValidEmail(request.getUsername()) && isValidPassword(request.getPassword())) {
            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .build();

            userRepository.save(user);
            return AuthResponseDto.builder()
                    .jwt(jwtService.getAccessToken(user))
                    .build();
        }
        return null;
    }

    /**
     * Updates the user profile based on the provided details.
     *
     * @param request The UserUpdateDto containing updated user information.
     * @return AuthResponseDto containing the updated JWT token.
     */
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
            if (isValidEmail(request.getUsername())) {
                user.setUsername(request.getUsername());
            } else {
                return null;
            }
        }

        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getPassword() != null) {
            if (isValidPassword(request.getPassword())) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            } else {
                return null;
            }
        }

        // Save the updated user to the database
        userRepository.save(user);

        // Generate a new JWT token for the updated user
        UserDetails updatedUserDetails = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Updated user not found"));

        // Generate a new access token with the updated email
        String newAccessToken = jwtService.getAccessToken(updatedUserDetails);

        // Generate a new refresh token
        return AuthResponseDto.builder()
                .jwt(newAccessToken)
                .build();
    }

    /**
     * Checks if the provided email address is in a valid format.
     *
     * @param email The email address to validate.
     * @return True if the email is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Checks if the provided password meets the specified criteria.
     *
     * @param password The password to validate.
     * @return True if the password is valid, false otherwise.
     */
    private boolean isValidPassword(String password) {
        // Check if the password meets all the criteria
        return password.length() >= 8 &&
        // contains at least one digit (number)
                password.matches(".*\\d.*") &&
                // contains at least one lowercase letter
                password.matches(".*[a-z].*") &&
                // contains at least one uppercase letter
                password.matches(".*[A-Z].*") &&
                // contains at least one special character from the provided list
                password.matches(".*[!@#$%^&*()-+=].*");
    }

}

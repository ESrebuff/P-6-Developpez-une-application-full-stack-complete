package com.openclassrooms.mddapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.AuthResponseDto;
import com.openclassrooms.mddapi.dto.LoginRequestDto;
import com.openclassrooms.mddapi.dto.RegisterRequestDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserUpdateDto;
import com.openclassrooms.mddapi.service.AuthService;
import com.openclassrooms.mddapi.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * Controller class for handling authentication and user profile operations.
 */
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    /**
     * Handles user login.
     *
     * @param request The LoginRequestDto containing login credentials.
     * @return ResponseEntity with the AuthResponseDto.
     */
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Handles user registration.
     *
     * @param request The RegisterRequestDto containing user registration details.
     * @return ResponseEntity with the AuthResponseDto if registration is
     *         successful, otherwise returns BAD_REQUEST.
     */
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request) {
        AuthResponseDto registrationResult = authService.register(request);

        if (registrationResult == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(registrationResult);
    }

    /**
     * Handles user profile update.
     *
     * @param request The UserUpdateDto containing updated user profile details.
     * @return ResponseEntity with the AuthResponseDto if update is successful,
     *         otherwise returns BAD_REQUEST.
     */
    @PutMapping(value = "me")
    public ResponseEntity<AuthResponseDto> updateProfile(@RequestBody UserUpdateDto request) {
        AuthResponseDto updateResult = authService.updateProfile(request);

        if (updateResult == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(updateResult);
    }

    /**
     * Retrieves the current user's profile.
     *
     * @return ResponseEntity with the UserDto if the user is found, otherwise
     *         returns NOT_FOUND.
     */
    @GetMapping(value = "me")
    public ResponseEntity<UserDto> getMyProfile() {
        UserDto userDto = userService.getCurrentUser();

        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDto);
    }
}

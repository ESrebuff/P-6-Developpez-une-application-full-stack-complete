package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.AuthResponseDto;
import com.openclassrooms.mddapi.dto.LoginRequestDto;
import com.openclassrooms.mddapi.dto.RegisterRequestDto;
import com.openclassrooms.mddapi.dto.UserUpdateDto;

public interface AuthServiceI {

    AuthResponseDto login(LoginRequestDto request);
    
    AuthResponseDto register(RegisterRequestDto request);

    AuthResponseDto updateProfile(UserUpdateDto request);

}

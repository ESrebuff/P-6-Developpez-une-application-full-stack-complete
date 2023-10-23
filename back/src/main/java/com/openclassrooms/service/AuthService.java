package com.openclassrooms.service;

import com.openclassrooms.mappers.dtos.AuthResponseDto;
import com.openclassrooms.mappers.dtos.LoginRequestDto;
import com.openclassrooms.mappers.dtos.RegisterRequestDto;

public interface AuthService {

    AuthResponseDto login(LoginRequestDto request);
    
    AuthResponseDto register(RegisterRequestDto request);

}

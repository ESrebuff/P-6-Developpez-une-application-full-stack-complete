package com.openclassrooms.service;

import com.openclassrooms.mappers.dtos.UserDto;

public interface UserService {

    UserDto getUserById(Integer id);
    
}

package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDto;

public interface UserService {

    UserDto getUserById(Integer id);
    
}

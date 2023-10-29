package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDto;

public interface UserServiceI {

    UserDto getUserById(Integer id);
    
}

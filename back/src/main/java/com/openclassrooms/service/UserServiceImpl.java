package com.openclassrooms.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.openclassrooms.mappers.dtos.UserDto;
import com.openclassrooms.model.User;
import com.openclassrooms.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @PostConstruct
    public void configureModelMapper() {
        modelMapper.createTypeMap(User.class, UserDto.class)
                .addMapping(User::getUsername, UserDto::setEmail)
                .addMapping(User::getName, UserDto::setName)
                .addMapping(User::getCreatedAt, UserDto::setCreated_at)
                .addMapping(User::getUpdatedAt, UserDto::setUpdated_at);
    }

    private UserDto mapToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return mapToUserDto(user);
        } else {
            return null;
        }
    }

}

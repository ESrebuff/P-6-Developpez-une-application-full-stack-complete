package com.openclassrooms.mddapi.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

/**
 * Service class for managing user-related operations in the application.
 */
@Service
@AllArgsConstructor
public class UserService implements UserServiceI {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    /**
     * Configures ModelMapper for mapping User to UserDto.
     */
    @PostConstruct
    public void configureModelMapper() {
        modelMapper.createTypeMap(User.class, UserDto.class)
                .addMapping(User::getUsername, UserDto::setUsername)
                .addMapping(User::getName, UserDto::setName)
                .addMapping(User::getCreatedAt, UserDto::setCreated_at)
                .addMapping(User::getUpdatedAt, UserDto::setUpdated_at);
    }

    /**
     * Retrieves a user by their ID and maps it to a UserDto.
     *
     * @param id The ID of the user to retrieve.
     * @return UserDto representing the retrieved user, or null if not found.
     */
    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return mapToUserDto(user);
        } else {
            return null;
        }
    }

    /**
     * Retrieves the currently authenticated user and maps it to a UserDto.
     *
     * @return UserDto representing the currently authenticated user, or null if not
     *         found.
     */
    @Override
    public UserDto getCurrentUser() {
        // Retrieve the currently authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Retrieve user by username
        User user = userRepository.findByUsername(username).orElse(null);

        // Map to UserDto
        if (user != null) {
            return mapToUserDto(user);
        } else {
            return null;
        }
    }

    private UserDto mapToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}

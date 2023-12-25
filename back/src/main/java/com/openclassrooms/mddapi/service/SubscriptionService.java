package com.openclassrooms.mddapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.SubscriptionId;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

/**
 * Service class for managing user subscriptions to subjects in the application.
 */
@Service
@AllArgsConstructor
public class SubscriptionService implements SubscriptionServiceI {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final SubjectService subjectService;

    /**
     * Configures ModelMapper for mapping Subscription to SubscriptionDto.
     */
    @PostConstruct
    public void configureModelMapper() {
        modelMapper.createTypeMap(Subscription.class, SubscriptionDto.class)
                .addMapping(src -> src.getUser().getId(), SubscriptionDto::setUserId)
                .addMapping(src -> src.getSubject().getId(), SubscriptionDto::setSubjectId);
    }

    /**
     * Retrieves all subscriptions for the currently authenticated user.
     *
     * @return List of SubscriptionDto objects representing user subscriptions.
     */
    @Override
    public List<SubscriptionDto> getAllSubscriptions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Subscription> userSubscriptions = subscriptionRepository.findByUser(user);

        return userSubscriptions.stream()
                .map(this::mapToSubscriptionDto)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new subscription for the currently authenticated user to a
     * specified subject.
     *
     * @param subjectId The ID of the subject to subscribe to.
     * @return SubscriptionDto representing the created subscription.
     */
    @Override
    public SubscriptionDto create(Integer subjectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Subscription newSubscription = new Subscription();
        Subject subject = modelMapper.map(subjectService.getSubject(subjectId), Subject.class);
        newSubscription.setUser(user);
        newSubscription.setSubject(subject);
        newSubscription.setSubscriptionDate(LocalDateTime.now());
        Subscription savedSubscription = subscriptionRepository.save(newSubscription);
        return mapToSubscriptionDto(savedSubscription);
    }

    /**
     * Deletes a subscription for the currently authenticated user to a specified
     * subject.
     *
     * @param subjectId The ID of the subject to unsubscribe from.
     */
    @Override
    public void delete(Integer subjectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        SubscriptionId subscriptionId = new SubscriptionId();
        subscriptionId.setUser(user.getId());
        subscriptionId.setSubject(subjectId);
        subscriptionRepository.deleteById(subscriptionId);
    }

    private SubscriptionDto mapToSubscriptionDto(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionDto.class);
    }
}

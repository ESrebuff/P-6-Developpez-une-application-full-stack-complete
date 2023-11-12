package com.openclassrooms.mddapi.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriptionService implements SubscriptionServiceI {

    private final SubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final SubjectService subjectService;

    @PostConstruct
    public void configureModelMapper() {
        modelMapper.createTypeMap(Subscription.class, SubscriptionDto.class)
                .addMapping(src -> src.getUser().getId(), SubscriptionDto::setUser)
                .addMapping(src -> src.getSubject().getId(), SubscriptionDto::setSubject);

    }

    private SubscriptionDto mapToSubscriptionDto(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionDto.class);
    }

    @Override
    public SubscriptionDto getSubscriptionById(Integer id) {
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if (subscription != null) {
            return mapToSubscriptionDto(subscription);
        }
        return null;
    }

    @Override
    public Map<String, List<SubscriptionDto>> getSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        List<SubscriptionDto> subscriptionDtos = subscriptions.stream()
                .map(this::mapToSubscriptionDto)
                .collect(Collectors.toList());

        Map<String, List<SubscriptionDto>> response = new HashMap<>();
        response.put("subscriptions", subscriptionDtos);

        return response;
    }

    @Override
    public SubscriptionDto create(Integer userId, Integer subjectId) {
        Subscription newSubscription = new Subscription();
        User user = modelMapper.map(userService.getUserById(userId), User.class);
        Subject subject = modelMapper.map(subjectService.getSubject(subjectId), Subject.class);
        newSubscription.setUser(user);
        newSubscription.setSubject(subject);
        newSubscription.setSubscriptionDate(LocalDateTime.now());
        Subscription savedSubscription = subscriptionRepository.save(newSubscription);
        return mapToSubscriptionDto(savedSubscription);
    }

    @Override
    public void delete(Integer id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
        }
    }
}

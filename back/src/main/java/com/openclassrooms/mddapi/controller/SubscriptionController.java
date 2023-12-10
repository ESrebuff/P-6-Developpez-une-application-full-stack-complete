package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.dto.SubscriptionRequestDto;
import com.openclassrooms.mddapi.dto.SubscriptionResponseDto;
import com.openclassrooms.mddapi.service.SubscriptionService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping()
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        List<SubscriptionDto> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    @PostMapping()
    public ResponseEntity<SubscriptionDto> subscribe(@RequestBody SubscriptionRequestDto request) {
        SubscriptionDto subscriptionDto = subscriptionService.create(request.getSubjectId());
        return ResponseEntity.ok(subscriptionDto);
    }

    @DeleteMapping("{subjectId}")
    public ResponseEntity<SubscriptionResponseDto> unsubscribe(
            @PathVariable Integer subjectId) {
        subscriptionService.delete(subjectId);
        SubscriptionResponseDto responseDTO = SubscriptionResponseDto.builder()
        .message("Unsubscribed successfully")
        .build();
        return ResponseEntity.ok(responseDTO);
    }

}

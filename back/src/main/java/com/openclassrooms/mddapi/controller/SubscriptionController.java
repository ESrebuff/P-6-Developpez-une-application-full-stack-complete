package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.SubscriptionRequestDto;
import com.openclassrooms.mddapi.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping()
    public ResponseEntity<String> subscribe(@RequestBody SubscriptionRequestDto request) {
        subscriptionService.create(request.getSubjectId());
        return ResponseEntity.ok("Subscribed successfully");
    }

    @DeleteMapping("{subjectId}")
    public ResponseEntity<String> unsubscribe(
            @PathVariable Integer subjectId) {
        subscriptionService.delete(subjectId);
        return ResponseEntity.ok("Unsubscribed successfully");
    }

}

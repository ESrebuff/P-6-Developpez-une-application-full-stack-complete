package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.dto.SubscriptionRequestDto;
import com.openclassrooms.mddapi.dto.SubscriptionResponseDto;
import com.openclassrooms.mddapi.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing subscriptions in the application.
 */
@RestController
@RequestMapping("/api/subscription")
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    /**
     * Retrieves all subscriptions.
     *
     * @return ResponseEntity with a list of SubscriptionDto.
     */
    @GetMapping()
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        List<SubscriptionDto> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    /**
     * Handles the subscription to a subject.
     *
     * @param request The SubscriptionRequestDto containing the subject ID to
     *                subscribe to.
     * @return ResponseEntity with the created SubscriptionDto.
     */
    @PostMapping()
    public ResponseEntity<SubscriptionDto> subscribe(@RequestBody SubscriptionRequestDto request) {
        SubscriptionDto subscriptionDto = subscriptionService.create(request.getSubjectId());
        return ResponseEntity.ok(subscriptionDto);
    }

    /**
     * Handles the unsubscription from a subject.
     *
     * @param subjectId The ID of the subject to unsubscribe from.
     * @return ResponseEntity with a SubscriptionResponseDto indicating successful
     *         unsubscription.
     */
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

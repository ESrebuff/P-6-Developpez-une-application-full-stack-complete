package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.model.Subject;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Integer id;
    private User user;
    private Subject subject;
    private LocalDateTime subscriptionDate;
}

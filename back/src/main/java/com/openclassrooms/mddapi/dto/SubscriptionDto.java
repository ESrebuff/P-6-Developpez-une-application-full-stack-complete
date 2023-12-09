package com.openclassrooms.mddapi.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Integer userId;
    private Integer subjectId;
    private LocalDateTime subscriptionDate;
}

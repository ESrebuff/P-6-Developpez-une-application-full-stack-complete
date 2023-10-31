package com.openclassrooms.mddapi.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Integer id;
    private Integer user_id;
    private Integer subject_id;
    private LocalDateTime subscription_date;
}

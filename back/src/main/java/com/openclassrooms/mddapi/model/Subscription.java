package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SUBSCRIPTIONS")
@IdClass(SubscriptionId.class)  // specifying the class for the composite primary key
public class Subscription {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    @PrePersist
    protected void onCreate() {
        subscriptionDate = LocalDateTime.now();
    }
}


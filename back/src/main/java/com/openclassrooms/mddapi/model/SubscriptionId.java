package com.openclassrooms.mddapi.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class SubscriptionId implements Serializable {
    private Integer user;
    private Integer subject;
}

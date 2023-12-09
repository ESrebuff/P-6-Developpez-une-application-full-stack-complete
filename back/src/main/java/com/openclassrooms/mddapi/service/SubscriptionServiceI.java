package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.SubscriptionDto;

public interface SubscriptionServiceI {

    List<SubscriptionDto> getAllSubscriptions();

    SubscriptionDto create(Integer subjectId);

    void delete(Integer subjectId);

}

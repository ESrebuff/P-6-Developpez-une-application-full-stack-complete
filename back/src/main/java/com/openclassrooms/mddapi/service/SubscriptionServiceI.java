package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;

public interface SubscriptionServiceI {

    SubscriptionDto create(Integer subjectId);

    void delete(Integer subjectId);

}

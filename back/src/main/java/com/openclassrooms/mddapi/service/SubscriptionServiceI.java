package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.Map;
import com.openclassrooms.mddapi.dto.SubscriptionDto;

public interface SubscriptionServiceI {

    SubscriptionDto getSubscriptionById(Integer id);

    Map<String, List<SubscriptionDto>> getSubscriptions();

    SubscriptionDto create(Integer userId, Integer subjectId);

    void delete(Integer id);

}

package org.example.channelservice.service.subscription;

import org.example.channelservice.domain.dto.request.SubscriptionRequest;
import org.example.channelservice.domain.dto.response.SubscriptionResponse;
import org.example.channelservice.entity.SubscriptionEntity;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    SubscriptionResponse create(SubscriptionRequest subscriptionRequest);
    SubscriptionEntity findById(UUID id);
    void delete(UUID id);
    List<SubscriptionResponse> findAllSubsBySubsId(UUID userId);
}


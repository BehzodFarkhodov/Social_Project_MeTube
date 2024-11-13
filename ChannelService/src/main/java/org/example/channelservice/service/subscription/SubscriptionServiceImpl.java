package org.example.channelservice.service.subscription;

import lombok.RequiredArgsConstructor;
import org.example.channelservice.clients.UserServiceClient;
import org.example.channelservice.domain.dto.request.SubscriptionRequest;
import org.example.channelservice.domain.dto.response.ChannelResponse;
import org.example.channelservice.domain.dto.response.SubscriptionResponse;
import org.example.channelservice.domain.dto.response.UserResponse;
import org.example.channelservice.entity.SubscriptionEntity;
import org.example.channelservice.exception.BaseException;
import org.example.channelservice.repository.SubscriptionRepository;
import org.example.channelservice.service.channel.ChannelService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserServiceClient userServiceClient;
    private final ChannelService channelService;

    @Override
    public SubscriptionResponse create(SubscriptionRequest subscriptionRequest) {
        UserResponse userResponse = userServiceClient.getUser(subscriptionRequest.getSubscriberId());
        ChannelResponse channelResponse = channelService.findById(subscriptionRequest.getChannelId());
        if (userResponse == null) {
            throw new BaseException("User not found", HttpStatus.NOT_FOUND.value());
        }
        if (channelResponse == null) {
            throw new BaseException("Channel not found", HttpStatus.NOT_FOUND.value());
        }
        Optional<SubscriptionEntity> existingSubscription = subscriptionRepository
                .findBySubscriberIdAndChannelId(subscriptionRequest.getSubscriberId(), subscriptionRequest.getChannelId());
        if (existingSubscription.isPresent()) {
            throw new IllegalStateException("This user is already subscribed to the channel.");
        }
        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setSubscriberId(subscriptionRequest.getSubscriberId());
        subscriptionEntity.setChannelId(subscriptionRequest.getChannelId());
        subscriptionRepository.save(subscriptionEntity);
         channelService.incrementSubscribeCount(subscriptionRequest.getChannelId());
        return SubscriptionResponse.builder()
                .subscriberId(subscriptionEntity.getSubscriberId())
                .channelId(subscriptionEntity.getChannelId())
                .build();
    }

    @Override
    public SubscriptionEntity findById(UUID id) {
        return subscriptionRepository.findById(id).orElseThrow(
                () -> new BaseException("Subscription not found", HttpStatus.NOT_FOUND.value())
        );
    }

    @Override
    public void delete(UUID id) {
        SubscriptionEntity subscription = findById(id);
        UserResponse userResponse = userServiceClient.getUser(subscription.getSubscriberId());
        if (userResponse == null) {
            throw new BaseException("User not found", HttpStatus.NOT_FOUND.value());
        }
        ChannelResponse channelResponse = channelService.findById(subscription.getChannelId());
        if (channelResponse == null) {
            throw new BaseException("Channel not found", HttpStatus.NOT_FOUND.value());
        }
        subscriptionRepository.delete(subscription);
        removeSubscriber(subscription.getChannelId(), subscription.getSubscriberId());
        channelService.decrementSubscribeCount(subscription.getChannelId());
    }

@Override
public List<SubscriptionResponse> findAllSubsBySubsId(UUID userId) {
    UserResponse userResponse = userServiceClient.getUser(userId);
    if (userResponse == null) {
        throw new BaseException("User not found", HttpStatus.NOT_FOUND.value());
    }
    List<SubscriptionEntity> subscriptionEntities = subscriptionRepository.findBySubscriberId(userId);
    if (subscriptionEntities.isEmpty()) {
        throw new BaseException("No subscriptions found for this user", HttpStatus.NOT_FOUND.value());
    }
    return subscriptionEntities.stream()
            .map(subscription -> SubscriptionResponse.builder()
                    .subscriberId(subscription.getSubscriberId())
                    .channelId(subscription.getChannelId())
                    .build())
            .toList();
}


    public void removeSubscriber(UUID channelId, UUID subscriberId) {
        UserResponse userResponse = userServiceClient.getUser(subscriberId);
        if (userResponse == null) {
            throw new BaseException("User not found", HttpStatus.NOT_FOUND.value());
        }
        ChannelResponse channelResponse = channelService.findById(channelId);
        if (channelResponse == null) {
            throw new BaseException("Channel not found", HttpStatus.NOT_FOUND.value());
        }

    }
}









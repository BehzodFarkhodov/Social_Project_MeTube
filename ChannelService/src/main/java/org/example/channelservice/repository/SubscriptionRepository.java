package org.example.channelservice.repository;
import org.example.channelservice.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {
    Optional<SubscriptionEntity> findBySubscriberIdAndChannelId(UUID subscriberId, UUID channelId);
    List<SubscriptionEntity> findBySubscriberId(UUID subscriberId);
    void deleteByChannelId(UUID channelId);

}

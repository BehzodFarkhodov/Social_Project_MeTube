package org.example.channelservice.repository;

import org.example.channelservice.entity.FavoriteChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface FavoriteChannelRepository extends JpaRepository<FavoriteChannelEntity, UUID> {

    List<FavoriteChannelEntity> findByUserId(UUID userId);
    boolean existsByUserIdAndChannelId(UUID userId, UUID channelId);
    void deleteByUserIdAndChannelId(UUID userId, UUID channelId);
}

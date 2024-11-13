package org.example.channelservice.repository;

import org.example.channelservice.entity.ChannelReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChannelReportRepository extends JpaRepository<ChannelReportEntity,UUID> {
    boolean existsByUserIdAndChannelId(UUID userId, UUID channelId);
    void deleteByChannelId(UUID channelId);

}


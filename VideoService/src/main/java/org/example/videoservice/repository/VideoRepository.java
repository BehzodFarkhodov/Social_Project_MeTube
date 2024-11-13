package org.example.videoservice.repository;

import org.example.videoservice.domain.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, UUID> {
    List<VideoEntity> findAllByChannelId(UUID channelId);
}



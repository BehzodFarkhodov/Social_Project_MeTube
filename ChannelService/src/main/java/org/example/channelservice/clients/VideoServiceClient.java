package org.example.channelservice.clients;

import org.example.channelservice.config.FeignConfig;
import org.example.channelservice.domain.dto.response.VideoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "VIDEO-SERVICE", url = "http://localhost:8086", configuration = FeignConfig.class)
public interface VideoServiceClient {
    @GetMapping("/api/video/by-channel/{channelId}")
    List<VideoResponse> getVideosByChannelId(@PathVariable("channelId") UUID channelId);
}


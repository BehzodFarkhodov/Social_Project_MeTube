package org.example.videoservice.service;

import org.example.videoservice.domain.dto.requests.VideoRequest;
import org.example.videoservice.domain.dto.response.VideoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface VideoService {

        VideoResponse saveVideo(VideoRequest videoRequest, MultipartFile multipartFile);
        VideoResponse getVideo(UUID videoId);
        void deleteVideo(UUID videoId);
        List<VideoResponse> getVideosByChannelId(UUID channelId);


}

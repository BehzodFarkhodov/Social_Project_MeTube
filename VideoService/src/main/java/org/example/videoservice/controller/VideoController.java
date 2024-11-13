package org.example.videoservice.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.example.videoservice.domain.dto.requests.VideoRequest;
import org.example.videoservice.domain.dto.response.VideoResponse;
import org.example.videoservice.domain.entity.VideoEntity;
import org.example.videoservice.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
@MultipartConfig(maxFileSize = 20 * 1024 * 1024,
        maxRequestSize = 50 * 1024 * 1024,
        fileSizeThreshold = 10 * 1024)
public class VideoController {
    private final VideoService videoService;
    @PostMapping("/create")
    public ResponseEntity<VideoResponse> createVideo(@RequestPart ("jsonData")VideoRequest videoRequest,
                                                     @RequestPart("video") MultipartFile videoFile) {
        VideoResponse savedVideo = videoService.saveVideo(videoRequest,videoFile);
        return new ResponseEntity<>(savedVideo, HttpStatus.CREATED);
    }

    @GetMapping("/finById/{videoId}")
    public ResponseEntity<VideoResponse> getVideo(@PathVariable UUID videoId) {
        VideoResponse videoResponse = videoService.getVideo(videoId);
        return ResponseEntity.ok(videoResponse);
    }

    @DeleteMapping("delete/{videoId}")
    public ResponseEntity<Void> deleteVideo(@PathVariable UUID videoId) {
        videoService.deleteVideo(videoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/by-channel/{channelId}")
    public ResponseEntity<List<VideoResponse>> getVideosByChannelId(@PathVariable UUID channelId) {
        List<VideoResponse> videos = videoService.getVideosByChannelId(channelId);
        return ResponseEntity.ok(videos);
    }









}

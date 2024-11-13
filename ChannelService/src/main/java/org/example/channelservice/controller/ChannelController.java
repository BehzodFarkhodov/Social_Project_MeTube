package org.example.channelservice.controller;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.example.channelservice.clients.VideoServiceClient;
import org.example.channelservice.domain.dto.request.ChannelRequest;
import org.example.channelservice.domain.dto.request.ChannelUpdateRequest;
import org.example.channelservice.domain.dto.request.SubscriptionRequest;
import org.example.channelservice.domain.dto.response.ChannelResponse;
import org.example.channelservice.domain.dto.response.FavoriteChannelResponse;
import org.example.channelservice.domain.dto.response.SubscriptionResponse;
import org.example.channelservice.domain.dto.response.VideoResponse;
import org.example.channelservice.exception.BaseException;
import org.example.channelservice.service.channel.ChannelService;
import org.example.channelservice.service.favorite.FavoriteChannelService;
import org.example.channelservice.service.subscription.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/channel")
@RequiredArgsConstructor
@MultipartConfig(maxFileSize = 10 * 1024 * 1024,
maxRequestSize = 50 * 1024 * 1024,
fileSizeThreshold = 10 * 1024)
public class ChannelController {

    private final ChannelService channelService;
    private final SubscriptionService subscriptionService;
    private final VideoServiceClient videoServiceClient;
    private final FavoriteChannelService favoriteChannelService;




    @PostMapping(value = "/create")
    public ResponseEntity<ChannelResponse> createChannel(
            @RequestPart("imageFile") MultipartFile imageFile,
            @RequestPart("jsonData") ChannelRequest channelRequest) {
        ChannelResponse savedChannel = channelService.save(channelRequest, imageFile);
        return new ResponseEntity<>(savedChannel, HttpStatus.CREATED);
    }




    @GetMapping("/{id}")
    public ResponseEntity<ChannelResponse> getChannelById(@PathVariable UUID id) {
        ChannelResponse channel = channelService.findById(id);
        return ResponseEntity.ok(channel);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ChannelResponse> updateChannel(@PathVariable UUID id, @RequestBody ChannelUpdateRequest updateRequest) {
        ChannelResponse updatedChannel = channelService.updateChannel(id, updateRequest);
        return ResponseEntity.ok(updatedChannel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteChannel(@PathVariable UUID id) {
        channelService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ChannelResponse>> getAllChannels() {
        List<ChannelResponse> channels = channelService.findAll();
        return ResponseEntity.ok(channels);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ChannelResponse>> getChannelsByNicknameOrName(@RequestParam("search") String searchValue) {
        List<ChannelResponse> channels = channelService.findByNicknameOrName(searchValue);
        return ResponseEntity.ok(channels);
    }


    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ChannelResponse>> getAllChannelsByOwnerId(@PathVariable UUID ownerId) {
        List<ChannelResponse> channels = channelService.findAllByOwnerId(ownerId);
        return ResponseEntity.ok(channels);
    }


    @PostMapping("/report/{userId}")
    public ResponseEntity<Void> reportChannel(
            @PathVariable  UUID userId,
            @RequestPart ("nickName") String nickName) {
        channelService.reportChannelByNickname(nickName,userId);
        return ResponseEntity.ok().build();
    }



//    @GetMapping("/{channelId}/videos/count")
//    public ResponseEntity<Long> getVideoCountByChannel(@PathVariable UUID channelId) {
//        long videoCount = channelService.countVideosByChannelId(channelId);
//        return ResponseEntity.ok(videoCount);
//    }

    @PostMapping("subscribe/create")
    public ResponseEntity<SubscriptionResponse> create(@RequestBody SubscriptionRequest subscriptionRequest) {
        SubscriptionResponse response = subscriptionService.create(subscriptionRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("subscribe/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        subscriptionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("subscribe/user/{userId}")
    public ResponseEntity<List<SubscriptionResponse>> getAllSubscriptionsByUserId(@PathVariable UUID userId) {
        List<SubscriptionResponse> allSubsBySubsId = subscriptionService.findAllSubsBySubsId(userId);
        return ResponseEntity.ok(allSubsBySubsId);
    }

    @DeleteMapping("subscriber/delete/{subscriberId}")
    public ResponseEntity<Void> removeSubscriber(
            @PathVariable UUID subscriberId) {
        try {
            subscriptionService.delete(subscriberId);
            return ResponseEntity.noContent().build();
        } catch (BaseException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    @GetMapping("/videos/{channelId}")
    public ResponseEntity<List<VideoResponse>> getVideosByChannelId(@PathVariable UUID channelId) {
        List<VideoResponse> videos = videoServiceClient.getVideosByChannelId(channelId);
        return ResponseEntity.ok(videos);
    }

    ////////////////////////// favorite channel

    @PostMapping("/favorite/channel")
    public String addFavoriteChannel(@RequestParam UUID userId, @RequestParam UUID channelId) {

        favoriteChannelService.addFavoriteChannel(userId, channelId);
        return "Channel added to favorites!";
    }

    @GetMapping("/favorite/{userId}")
    public List<FavoriteChannelResponse> getFavoriteChannels(@PathVariable UUID userId) {
        return favoriteChannelService.getFavoriteChannels(userId);
    }

    @DeleteMapping("/favorite/remove")
    public String removeFavoriteChannel(@RequestParam UUID userId, @RequestParam UUID channelId) {
        favoriteChannelService.removeFavoriteChannel(userId, channelId);
        return "Channel removed from favorites!";
    }

    @PostMapping("/toggle")
    public String toggleFavoriteChannel(@RequestParam UUID userId, @RequestParam UUID channelId) {
        favoriteChannelService.toggleFavoriteChannel(userId, channelId);
        return "Channel favorite status toggled successfully!";
    }




}


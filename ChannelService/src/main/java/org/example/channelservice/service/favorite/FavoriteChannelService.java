package org.example.channelservice.service.favorite;

import org.example.channelservice.domain.dto.response.FavoriteChannelResponse;

import java.util.List;
import java.util.UUID;

public interface FavoriteChannelService {

    void addFavoriteChannel(UUID userId, UUID channelId);
    List<FavoriteChannelResponse> getFavoriteChannels(UUID userId);
    void removeFavoriteChannel(UUID userId, UUID channelId);
    void toggleFavoriteChannel(UUID userId, UUID channelId);

}

package org.example.channelservice.service.favorite;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.channelservice.domain.dto.response.ChannelResponse;
import org.example.channelservice.domain.dto.response.FavoriteChannelResponse;
import org.example.channelservice.entity.FavoriteChannelEntity;
import org.example.channelservice.repository.FavoriteChannelRepository;
import org.example.channelservice.service.channel.ChannelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteChannelServiceImpl implements FavoriteChannelService {


    private final FavoriteChannelRepository favoriteChannelRepository;
    private final ChannelService channelService;
    @Override
    public void addFavoriteChannel(UUID userId, UUID channelId) {
        if (favoriteChannelRepository.existsByUserIdAndChannelId(userId, channelId)) {
            throw new RuntimeException("Channel is already in favorites.");
        }

        FavoriteChannelEntity favoriteChannel = new FavoriteChannelEntity();
        favoriteChannel.setUserId(userId);
        favoriteChannel.setChannelId(channelId);
        favoriteChannelRepository.save(favoriteChannel);
    }

    @Override
    public List<FavoriteChannelResponse> getFavoriteChannels(UUID userId) {
        List<FavoriteChannelEntity> favoriteChannels = favoriteChannelRepository.findByUserId(userId);

        return favoriteChannels.stream().map(favorite -> {
            ChannelResponse channelInfo = channelService.findById(favorite.getChannelId());
            return new FavoriteChannelResponse(favorite.getChannelId(), channelInfo.getName(), channelInfo.getDescription());
        }).collect(Collectors.toList());
    }

    @Override
    public void removeFavoriteChannel(UUID userId, UUID channelId) {
        if (!favoriteChannelRepository.existsByUserIdAndChannelId(userId, channelId)) {
            throw new RuntimeException("Channel is not in favorites.");
        }
        favoriteChannelRepository.deleteByUserIdAndChannelId(userId, channelId);
    }

     @Transactional
    @Override
    public void toggleFavoriteChannel(UUID userId, UUID channelId) {
        if (favoriteChannelRepository.existsByUserIdAndChannelId(userId, channelId)) {
            favoriteChannelRepository.deleteByUserIdAndChannelId(userId, channelId);
        } else {
            FavoriteChannelEntity favoriteChannel = new FavoriteChannelEntity();
            favoriteChannel.setUserId(userId);
            favoriteChannel.setChannelId(channelId);
            favoriteChannelRepository.save(favoriteChannel);
        }
    }
}

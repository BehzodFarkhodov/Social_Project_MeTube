package org.example.channelservice.domain.dto.response;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChannelResponse {
    private String name;
    private String description;
    private String imagePath;
    private String nickName;
    private UUID ownerId;
    private Integer subscriberCount;
    private List<VideoResponse> videos;
}






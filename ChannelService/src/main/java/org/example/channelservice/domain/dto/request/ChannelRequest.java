package org.example.channelservice.domain.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChannelRequest {
    private String name;
    private String description;
    private String imagePath;
    private String nickName;
    private UUID ownerId;
}




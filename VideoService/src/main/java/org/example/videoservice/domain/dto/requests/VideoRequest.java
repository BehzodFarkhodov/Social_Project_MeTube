package org.example.videoservice.domain.dto.requests;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VideoRequest {
    private String title;
    private String description;
    private String videoUrl;
    private UUID channelId;

}



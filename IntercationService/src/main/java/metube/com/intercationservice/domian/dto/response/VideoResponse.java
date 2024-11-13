package metube.com.intercationservice.domian.dto.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VideoResponse {
    private String title;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;
    private Integer views;
    private UUID channelId;
}

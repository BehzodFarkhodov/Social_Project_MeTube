package org.example.channelservice.domain.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoResponse {
    private UUID id;
    private String title;
    private String description;
    private String videoUrl;
    private Integer views;
}

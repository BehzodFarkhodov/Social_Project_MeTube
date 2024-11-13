package metube.com.intercationservice.domian.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HistoryRes {
    private UUID userId;
    private UUID videoId;
    private LocalDateTime watchedTime;
}

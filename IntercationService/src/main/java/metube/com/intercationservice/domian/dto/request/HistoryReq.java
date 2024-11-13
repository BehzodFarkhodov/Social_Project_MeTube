package metube.com.intercationservice.domian.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HistoryReq {
    private UUID userId;
    private UUID videoId;
    private LocalDateTime watchedTime = LocalDateTime.now();
}

package metube.com.intercationservice.domian.dto.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LikeCommitRes {
    private UUID userId;
    private UUID commitId;
}

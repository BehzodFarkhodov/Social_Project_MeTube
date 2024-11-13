package metube.com.intercationservice.domian.dto.response;

import lombok.*;
import metube.com.intercationservice.domian.entity.CommitEntity;
import metube.com.intercationservice.domian.entity.LikeEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommitRes {
    private UUID id;
    private UUID userId;
    private String comment;
    private UUID videoId;
    private Set<LikeEntity> likes;

}

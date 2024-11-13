package metube.com.intercationservice.domian.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LikeCommitReq {
    private UUID userId;
    private UUID commitId;
}

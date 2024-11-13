package metube.com.intercationservice.domian.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommitReq {
    private UUID userId;
    private String comment;
    private UUID videoId;
}

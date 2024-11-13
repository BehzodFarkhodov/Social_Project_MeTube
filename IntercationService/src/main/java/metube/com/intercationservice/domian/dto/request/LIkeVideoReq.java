package metube.com.intercationservice.domian.dto.request;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LIkeVideoReq {
    private UUID userId;
    private UUID videoId;
}

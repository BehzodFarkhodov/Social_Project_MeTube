package metube.com.intercationservice.domian.dto.response;

import lombok.*;
import metube.com.intercationservice.domian.enums.ReportType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReportRes {
    private UUID userId;
    private ReportType type;
    private UUID videoId;
}

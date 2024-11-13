package metube.com.intercationservice.domian.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metube.com.intercationservice.domian.enums.ReportType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportReq {
    private UUID userId;
    private ReportType type;
    private UUID videoId;
}

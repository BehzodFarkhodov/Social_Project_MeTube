package metube.com.intercationservice.domian.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import metube.com.intercationservice.domian.enums.ReportType;

import java.util.UUID;

@Entity


@Table(name = "report")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReportEntity extends BaseEntity {
    private UUID userId;
    private ReportType type;
    private UUID videoId;
}

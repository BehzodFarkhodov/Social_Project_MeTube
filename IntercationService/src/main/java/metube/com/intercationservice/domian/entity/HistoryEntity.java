package metube.com.intercationservice.domian.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HistoryEntity extends BaseEntity {
    private UUID userId;
    private UUID videoId;
    private LocalDateTime watchedTime;
}





package metube.com.intercationservice.domian.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity

@Table(name = "likes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LikeEntity extends BaseEntity{
    private UUID userId;
    private UUID videoId;
}

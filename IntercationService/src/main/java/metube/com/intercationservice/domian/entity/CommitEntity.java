package metube.com.intercationservice.domian.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity

@Table(name = "commits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommitEntity extends BaseEntity {

    private UUID userId;
    private String comment;
    private UUID videoId;

    @OneToMany
    private Set<LikeEntity> likes;
}

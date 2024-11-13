package org.example.videoservice.domain.entity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "videos")
public class VideoEntity extends BaseEntity {
      private String title;
      private String description;
      private String videoUrl;
      private String thumbnailUrl;
      private Integer views;
      private UUID commentId;
      private UUID likeId;
      private UUID channelId;
}




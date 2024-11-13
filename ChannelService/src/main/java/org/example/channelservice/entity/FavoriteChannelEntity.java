package org.example.channelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "favorite_channels")
public class FavoriteChannelEntity extends BaseEntity {
    private UUID userId;
    private UUID channelId;
}

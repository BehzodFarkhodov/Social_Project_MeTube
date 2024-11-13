package org.example.channelservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "channels")
public class ChannelEntity extends BaseEntity {
    private String name;
    private String description;
    private String imagePath;
    private String nickName;
    private UUID ownerId;
    private Integer subscriberCount;
    private Integer complaintCount;
}











package org.example.channelservice.domain.dto.response;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubscriptionResponse {
    private UUID subscriberId;
    private UUID channelId;
}

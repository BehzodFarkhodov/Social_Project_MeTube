package org.example.channelservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import metube.com.dto.request.UserNotificationRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChannelProducer {

    private final KafkaTemplate<String, UserNotificationRequest> kafkaTemplate;


    public void produce(String key, UserNotificationRequest userNotificationRequest) {
        CompletableFuture<SendResult<String, UserNotificationRequest>> auth = kafkaTemplate.send("auth", key, userNotificationRequest);

        auth.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error sending message to kafka topic : {}", ex.getMessage());
                return;
            }
            log.info("Message sent to kafka topic : {} {}", result.getProducerRecord().topic(), result.getProducerRecord().value());
        });
    }

}

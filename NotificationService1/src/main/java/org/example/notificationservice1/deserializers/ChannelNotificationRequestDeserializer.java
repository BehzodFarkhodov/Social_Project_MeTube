package org.example.notificationservice1.deserializers;//package org.example.authservice.deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import metube.com.dto.request.UserNotificationRequest;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;



import java.io.IOException;
import java.util.Map;

public class ChannelNotificationRequestDeserializer implements Deserializer<UserNotificationRequest> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
/*
        Deserializer.super.configure(configs, isKey);
*/
    }

    @Override
    public UserNotificationRequest deserialize(String topic, byte[] data) {

        try {
            if (data == null) return null;

            return objectMapper.readValue(data, UserNotificationRequest.class);
        } catch (IOException e) {

            throw new SerializationException("Error when deserializing UserNotificationRequest", e);
        }


    }

    @Override
    public void close() {
        /*Deserializer.super.close();*/
    }
}

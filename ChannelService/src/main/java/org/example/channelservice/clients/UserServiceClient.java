package org.example.channelservice.clients;
import org.example.channelservice.config.FeignConfig;
import org.example.channelservice.domain.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;


@FeignClient(name = "AUTH-SERVICE",configuration = FeignConfig.class)
public interface UserServiceClient {

    @GetMapping("/api/auth/{id}")
    UserResponse getUser(@PathVariable("id") UUID id);


}

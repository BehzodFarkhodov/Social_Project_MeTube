package metube.com.intercationservice.clients;

import metube.com.intercationservice.config.FeignConfig;
import metube.com.intercationservice.domian.dto.response.VideoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "VIDEO-SERVICE",url = "http://localhost:8086", configuration = FeignConfig.class)
public interface VideoServiceClient {
    @GetMapping("/api/video/finById/{id}")
    VideoResponse getVideo(@PathVariable("id") UUID id);


    @DeleteMapping("/api/video/delete/{id}")
    void delete(@PathVariable("id") UUID id);
}
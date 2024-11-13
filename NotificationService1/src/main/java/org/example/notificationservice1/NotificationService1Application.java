package org.example.notificationservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationService1Application {

    public static void main(String[] args) {
        SpringApplication.run(NotificationService1Application.class, args);
    }

}

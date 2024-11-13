package org.example.channelservice.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignConfig {


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = (String) authentication.getPrincipal();
/*
            String roles = authentication.getAuthorities().stream().findFirst().get().getAuthority();
*/
            requestTemplate.header("Authorization",username);
            /*requestTemplate.header("X-Roles",roles);*/

        };

    }
  /*  @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = (String) authentication.getPrincipal();
            requestTemplate.header("X-Username",username);

        };
    }*/
//@Bean
//public RequestInterceptor requestInterceptor() {
//    return requestTemplate -> {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() instanceof String) {
//            String username = (String) authentication.getPrincipal();
//            requestTemplate.header("X-Username", username);
//        }
//    };
//}

}



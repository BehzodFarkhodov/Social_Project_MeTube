package org.example.authservice.config;


import org.example.authservice.filter.CustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final String[] WHITE_LIST = {"/api/auth/login",
            "/api/auth/register","api/auth/verifyEmail","api/auth/loginWithEmail",
            "/api/auth/swagger-ui/**",
            "/api/auth/v3/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
//            "http://64.226.102.195:8080/api/auth/swagger-ui/index.html#/"};
            "http://192.168.112.6:8084/api/auth/login"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // CORS ni yoqing
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(WHITE_LIST).permitAll()  // Agar sizda maxsus ruxsatlar bo'lsa
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new CustomFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*"); // Barcha domenlarga ruxsat berish uchun allowedOriginPattern ishlatish
        config.setAllowCredentials(false); // allowCredentials true bo'lsa, allowedOriginPattern ishlatilishi kerak
        config.addAllowedHeader("*"); // Barcha headerlarga ruxsat berish
        config.addAllowedMethod("*"); // Barcha HTTP metodlarga ruxsat berish

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // CORS sozlamalarni barcha yo'llarga qo'llash
        return source;
    }

}



package metube.com.intercationservice.config;

import jakarta.ws.rs.HttpMethod;
import metube.com.intercationservice.filter.CustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final String[] WHITE_LIST = {"/api/auth/login",
            "/api/auth/register",
            "/api/interaction/swagger-ui/**",
            "/api/interaction/v3/api-docs/**",
            "/v3/api-docs/",
            "/swagger-ui/",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(WHITE_LIST).permitAll()

                                .requestMatchers(HttpMethod.GET, "/api/commit/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/commit/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/commit/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/commit/**").authenticated()

                                .requestMatchers(HttpMethod.GET, "/api/history/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/history/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/history/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/history/**").authenticated()

                                .requestMatchers(HttpMethod.GET, "/api/like/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/like/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/like/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/like/**").authenticated()

                                .requestMatchers(HttpMethod.GET, "/api/report/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/report/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/report/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/report/**").authenticated()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new CustomFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

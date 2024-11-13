package uz.sb.apigateway.filter;

import io.jsonwebtoken.Claims;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import uz.sb.apigateway.service.JwtService;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {


    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    public static class Config {
        // Put configuration properties here
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return chain.filter(exchange);
            }

            String token = authHeader.substring(7);
            try {
                Claims claims = jwtService.parseToken(token);

                String username = claims.getSubject();
                /* String roles = claims.get("roles").toString();*/

                exchange.getRequest().mutate()
                        .header("X-Username", username)
//                        .header("X-roles", "ROLE_" + roles)
                        .build();
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT token");
            }
            return chain.filter(exchange);
        };
    }
}

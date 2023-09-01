package yoon.test.cloud.scgTest1.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import yoon.test.cloud.scgTest1.jwt.JwtUtil;

@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    private final JwtUtil jwtUtil;
    public static class Config{}
    public AuthenticationGatewayFilterFactory(JwtUtil jwtUtil){
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain)->{
            String token = exchange.getRequest().getHeaders().get("Authorization").get(0).substring(7);

            return chain.filter(exchange);
        };
    }

}

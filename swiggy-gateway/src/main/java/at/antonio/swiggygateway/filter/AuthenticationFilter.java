package at.antonio.swiggygateway.filter;

import at.antonio.swiggygateway.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private RouteValidator routeValidator;
    private RestTemplate restTemplate;

    public AuthenticationFilter(RouteValidator routeValidator, RestTemplate restTemplate) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.restTemplate = restTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange,chain) -> {
            if(routeValidator.needsValidation.test(exchange.getRequest())) {
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Header does not contain AUTHORIZATION!");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader != null && authHeader.contains("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    //REST CALL for validation: possible, but not a good solution
                    //restTemplate.getForObject("http://IDENTITY-SERVICE/validate?token="+authHeader, String.class);

                    JwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    throw new RuntimeException("Invalid AUTHORIZATION value!");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}

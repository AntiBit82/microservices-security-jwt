package at.antonio.swiggygateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
  private List<String> openRoutes =
      List.of("/auth/register", "/auth/token", "/eureka");

  public Predicate<ServerHttpRequest> needsValidation =
      (request -> openRoutes.stream().noneMatch(uri -> request.getURI().getPath().contains(uri)));
}

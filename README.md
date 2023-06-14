# microservices-security-jwt

- swiggy-app = main service (default port 8081) which calls
- restaurant-service on (default port 8082) for an order-status
- swiggy-gateway (port 8080) contains the routes to both (configured in application.yml) and relies on
- swiggy-discovery-server (Eureka)

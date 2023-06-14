package at.antonio.swiggydiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SwiggyDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiggyDiscoveryServerApplication.class, args);
	}

}

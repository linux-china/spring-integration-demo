package org.mvnsearch.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.http.config.EnableIntegrationGraphController;

@SpringBootApplication
@EnableIntegrationGraphController(path = "/testIntegration")
public class IntegrationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationDemoApplication.class, args);
    }
}

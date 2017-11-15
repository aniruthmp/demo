package com.example.demo.config;

import com.example.demo.client.CoursesClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DemoConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CoursesClient coursesClient(RestTemplate restTemplate,
                                       @Value("${course-service.host:http://localhost:9090}") String uri) {
        return new CoursesClient(restTemplate, uri);
    }
}

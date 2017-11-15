package com.example.demo.client;

import com.example.demo.client.model.Course;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Service
public class CoursesClient {

    private final RestTemplate restTemplate;
    private final String uri;

    public CoursesClient(RestTemplate restTemplate, String uri) {
        this.restTemplate = restTemplate;
        this.uri = uri;
    }

    public Collection<Course> getAllCourses() {
        ParameterizedTypeReference<Collection<Course>> ptr =
                new ParameterizedTypeReference<Collection<Course>>() {};
        return restTemplate.exchange(this.uri + "/courses", HttpMethod.GET, null, ptr)
                .getBody();
    }

    public Course getCourseById(Long id) {
        return restTemplate.exchange(this.uri + "/courses/{id}", HttpMethod.GET, null, Course.class, id)
                .getBody();
    }
}

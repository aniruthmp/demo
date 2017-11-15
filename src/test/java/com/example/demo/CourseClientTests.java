package com.example.demo;

import com.example.demo.client.CoursesClient;
import com.example.demo.client.model.Course;
import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseClientTests {

    private MockRestServiceServer mockRestServiceServer;
    private Resource courses = new ClassPathResource("courses.json");
    private Resource courseById = new ClassPathResource("course-by-id.json");

    @Autowired
    private CoursesClient client;
    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void before() {
        this.mockRestServiceServer = MockRestServiceServer.bindTo(this.restTemplate).build();
    }

    @Test
    public void clientShouldReturnAllCourses() throws Exception {
        this.mockRestServiceServer.expect(ExpectedCount.manyTimes(), requestTo("http://localhost:9090/courses"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(this.courses, MediaType.APPLICATION_JSON_UTF8));
        Collection<Course> courses = client.getAllCourses();
        BDDAssertions.then(courses.size()).isEqualTo(2);
    }

    @Test
    public void courseByIdShouldReturnACourse() throws Exception {
        this.mockRestServiceServer.expect(ExpectedCount.manyTimes(), requestTo("http://localhost:9090/courses/101"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(this.courseById, MediaType.APPLICATION_JSON_UTF8));
        Course course = client.getCourseById(101L);
        BDDAssertions.then(course.getCourseId()).isEqualTo(101L);
    }
}

package com.example.demo;

import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@DataJpaTest
public class RepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveShouldWork() throws Exception{
        Student student = new Student(null, "first", "last", "email@email.com");
        Student saved = this.testEntityManager.persistFlushFind(student);
        BDDAssertions.then(saved.getId()).isNotNull();

        BDDAssertions.then(saved.getFirstName()).isNotBlank();
        BDDAssertions.then(saved.getFirstName()).isEqualToIgnoringCase("first");

        BDDAssertions.then(saved.getLastName()).isNotBlank();
        BDDAssertions.then(saved.getLastName()).isEqualToIgnoringCase("last");

        BDDAssertions.then(saved.getEmail()).isNotBlank();
        BDDAssertions.then(saved.getEmail()).isEqualToIgnoringCase("email@email.com");
    }

}

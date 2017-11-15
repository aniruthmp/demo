package com.example.demo.api;

import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class StudentRestController {

    private StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(path = "/students/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Student getStudentById(@PathVariable Long id){
        return this.studentRepository.findOne(id);
    }

    @GetMapping(path = "/students", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<Student> getStudent(){
        return this.studentRepository.findAll();
    }
}

package com.example.demo;

import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void studentByIdShouldReturnAsJsonStudent() throws Exception {
        Mockito.when(this.studentRepository.findOne(Mockito.anyLong()))
                .thenReturn(new Student(1L, "first", "last", "email@email.com"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("@.id").value(1L));
    }

    @Test
    public void studentsShouldReturnAllStudents() throws Exception {
        Mockito.when(this.studentRepository.findAll())
                .thenReturn(Arrays.asList(new Student(1L, "first", "last", "email@email.com"),
                        new Student(2L, "first", "last", "email@email.com")));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].firstName").value("first"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].id").value(2L));
    }
}

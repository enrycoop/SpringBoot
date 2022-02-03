package com.example.SpringBootTest.studentController;

import com.example.SpringBootTest.student.Student;
import com.example.SpringBootTest.student.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@WebMvcTest
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setup(){
        student = new Student(1L,"ben", LocalDate.of(2020,02,02),"test.test@tets");
    }

    @Test
    @SneakyThrows
    void getStudent(){
        Long id = 1L;
        Mockito.when(studentService.getStudentById(id)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name")
                        .value(student.getName()));
    }

    @Test
    @SneakyThrows
    void addStudent(){
        Student inSt = new Student("ben", LocalDate.of(2020,02,02),"test.test@tets");
        Mockito.when(studentService.addNewStudent(inSt)).thenReturn(inSt);

        String json = "{\n" +
                "  \"name\": \"ben\",\n" +
                "  \"dob\": \"2020-02-02\",\n" +
                "  \"email\": \"test.test@tets\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

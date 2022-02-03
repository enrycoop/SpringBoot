package com.example.SpringBootTest.studentService;

import com.example.SpringBootTest.student.Student;
import com.example.SpringBootTest.student.StudentRepository;
import com.example.SpringBootTest.student.StudentService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        Student st = new Student(1L,"ben", LocalDate.of(2020,02,02),"test.test@tets");
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(st));
    }

    @SneakyThrows
    @Test
    @DisplayName("Get Data based on Valid student id")
    void whenValidStudentId_thenStudentShouldBeFound() {
        Long id = 1L;
        Student found = studentService.getStudentById(id);
        assertEquals(id, found.getId());   //questo è il vero test!!
    }
}
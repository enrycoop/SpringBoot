package com.example.SpringBootTest.studentRepository;

import com.example.SpringBootTest.student.Student;
import com.example.SpringBootTest.student.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setup() {
        Student student = new Student("ben", LocalDate.of(2020,02,02),"test.test@tets");
        entityManager.persist(student);
    }

    @Test
    public void findStudentById(){
        Student s = studentRepository.findById(1L).get();
        assertEquals("ben", s.getName());
    }
}

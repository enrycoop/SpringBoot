package com.example.SpringBootTest.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student ben = new Student(
                    "Ben",
                    //23,
                    LocalDate.of(1998,02,12),
                    "ben.test@test.com");
            Student mary = new Student(
                    "Mary",
                    //21,
                    LocalDate.of(2000,12,4),
                    "mary.demo@test.com");
            repository.saveAll(List.of(ben,mary));
        };
    }
}

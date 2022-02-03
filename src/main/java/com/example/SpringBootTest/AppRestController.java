package com.example.SpringBootTest;

import com.example.SpringBootTest.student.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class AppRestController {

    @GetMapping("/")
    /*public List<String> hello(){
        return List.of("Hello","World"); //By default return a JSON!!
    }*/
    public List<Student> hello(){
        return List.of(new Student(
                1L,
                "Ben",
               // 21,
                LocalDate.of(1995,2,21),
                "ben.ben@gmail.com"
        ));


    }
}

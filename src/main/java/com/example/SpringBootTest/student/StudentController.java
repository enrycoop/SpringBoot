package com.example.SpringBootTest.student;

import com.example.SpringBootTest.error.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        /*return List.of(new Student(
                1L,
                "Ben",
                21,
                LocalDate.of(1995,2,21),
                "ben.ben@gmail.com"
        ));*/
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}") //aggiungo al percorso file di RequestMapping un pezzo
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") Long id) throws StudentNotFoundException {
        Student s = studentService.getStudentById(id);
        return ResponseEntity.ok(s);

    }

    @PostMapping
    public void registerNewStudent(@Valid @RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}") //aggiungo al percorso file di RequestMapping un pezzo
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateStudent(id,name,email);
    }
}

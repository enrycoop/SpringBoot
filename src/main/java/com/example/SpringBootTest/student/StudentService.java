package com.example.SpringBootTest.student;

import com.example.SpringBootTest.error.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        /*return List.of(new Student(
                1L,
                "Ben",
                21,
                LocalDate.of(1995,2,21),
                "ben.ben@gmail.com"
        ));*/

        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) throws StudentNotFoundException {
        Student opSt = studentRepository.findById(id)
                .orElseThrow( () -> new StudentNotFoundException(
                "Student with id " + id + " does not exist"
                ) );
        return opSt;

    }

    public Student addNewStudent(Student student) {
        System.out.println("Adding new Student > "+ student);

        Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()){
            throw new IllegalStateException("email already in DB");
        }

        studentRepository.save(student);
        return student;
    }

    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalStateException("Student with id " +
                    id+" does not exist ");
        }
        studentRepository.deleteById(id);

    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow( () -> new IllegalStateException(
                        "Student with id " + id + " does not exist"
                ) );
        if (name != null &&
                name.length() > 0
                && !Objects.equals(student.getName(),name)){
            student.setName(name); //normalissimo SETTER ma agirà sul DB grazie a @Transactional
        }

        if(email != null &&
            email.length() >0 &&
            !Objects.equals(student.getEmail(),email)){

            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()){
                throw new IllegalStateException("Sorry, this email "
                        + email +" is already taken ");
            }
            student.setEmail(email);
        }
    }
}

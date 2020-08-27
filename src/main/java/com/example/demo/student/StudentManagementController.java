package com.example.demo.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping
    public List<Student> getStudents(){
        return STUDENTS;
    }

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Student " + studentId + " does not exists"
                ));
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println(String.format("Deleting %d student", studentId));
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("Registering 1 student");
    }

    @PutMapping("{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student student){
        System.out.println(String.format("Updating %d student", studentId));
    }


}

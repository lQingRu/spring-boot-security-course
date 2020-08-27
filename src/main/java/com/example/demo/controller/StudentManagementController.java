package com.example.demo.controller;

import com.example.demo.domain.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Student " + studentId + " does not exists"
                ));
    }

   // hasRole("ROLE_"); hasAuthority("permission"); hasAnyRole("ROLE_"); hasAnyAuthority("permission")

    @DeleteMapping("{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println(String.format("Deleting %d student", studentId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("Registering 1 student");
        System.out.println(student);
    }

    @PutMapping("{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student student){
        System.out.println(String.format("Updating %d student", studentId));
    }


}

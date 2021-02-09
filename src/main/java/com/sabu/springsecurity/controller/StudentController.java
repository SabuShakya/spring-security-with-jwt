package com.sabu.springsecurity.controller;

import com.sabu.springsecurity.dto.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Sabu Shakya"),
            new Student(3, "Maria Maria")
    );

    @GetMapping(path = "{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable("studentId") Integer studentId) {
        Student studentData = STUDENTS.stream().filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " doesnot exist."));
        return ResponseEntity.ok(studentData);
    }
}

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("courses")
public class CourseController {

    @GetMapping
    public String getCourses(){
        return "courses";
    }
}

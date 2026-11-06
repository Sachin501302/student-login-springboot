package com.example.crs.controller;

import com.example.crs.model.Course;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class CourseController {

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return List.of(
                new Course("CSIS1175", "Introduction to Programming", 3),
                new Course("CSIS2175", "Advanced Integrated Software Development", 3),
                new Course("CSIS3275", "Software Engineering", 3)
        );
    }
}

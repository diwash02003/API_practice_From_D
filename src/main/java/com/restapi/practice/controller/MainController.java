package com.restapi.practice.controller;

import com.restapi.practice.entity.Courses;
import com.restapi.practice.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final CourseService courseService;

    public MainController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping({"/","/home"})
    public  String home(){
        logger.info("Accessed home page");
        return "This is home page...!!!";
    }

    // get the request
    @GetMapping("/courses")
    public List<Courses> getCourses(){
        logger.info("Fetching all courses");
        return this.courseService.getCourses();
    }

    @GetMapping("/courses/{courseId}")
    public Courses getCourse(@PathVariable long courseId ){
        logger.info("Fetching course with ID: {}", courseId);
        return this.courseService.getCourse(courseId);
    }

    @PostMapping("/courses")
    public Courses addCourse(@RequestBody  Courses courses){
        logger.info("Adding new course: {}", courses);
        return this.courseService.addCourse(courses);
    }

    @DeleteMapping("/courses/id/{courseId}")
    public void deleteCourseById(@PathVariable long courseId){
        logger.info("Deleting course with ID: {}", courseId);
        this.courseService.deleteCourse(courseId);
    }
    @PutMapping("/courses/{courseId}")
    public Courses updateCourse(@PathVariable  long courseId, @RequestBody Courses course){
        return this.courseService.updateCourse(courseId,course);
    }

    @GetMapping("/courses/name/{courseName}")
    public Courses findCourseByName(@PathVariable String courseName){
        logger.info("Fetching course with courseName: {}", courseName);
        return  this.courseService.getCourseByName(courseName);
    }
}

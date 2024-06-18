package com.restapi.practice.controller;

import com.restapi.practice.entity.Courses;
import com.restapi.practice.exception.CourseNotFoundException;
import com.restapi.practice.exception.CourseServiceException;
import com.restapi.practice.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MainController {
    //Logger class for l
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    //CourseService instance for business logic
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

    @GetMapping("/courses/id/{courseId}")
    public ResponseEntity<Courses> getCourse(@PathVariable long courseId ){
        logger.info("Fetching course with ID: {}", courseId);
        try{
            Courses course =this.courseService.getCourse(courseId);
            return new ResponseEntity<>(course,HttpStatus.OK);
        } catch (CourseNotFoundException e){
            logger.error("Course not found with ID: {}", courseId);
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("Error fetching course with ID: {}", courseId, e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/courses")
    public ResponseEntity<Courses> addCourse(@RequestBody  Courses courses){
        logger.info("Adding new course: {}", courses);
        try {
            Courses addedCourse = courseService.addCourse(courses);
            return new ResponseEntity<>(addedCourse,HttpStatus.OK);
        } catch (Exception e){
            logger.error("Error adding new course: {}", courses, e);
            return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable long id){
        try{
            boolean isDeleted = this.courseService.deleteCourse(id);
            if(isDeleted){
                return new ResponseEntity<>(HttpStatus.OK);
            } else  {
                throw new CourseNotFoundException("Course not found with ID: " + id);
            }
        }catch (CourseNotFoundException e){
            logger.error(e.getMessage());
            return  new ResponseEntity<>("Course not found",HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("Error deleting course with id " + id, e);
            throw new CourseServiceException("Error deleting course", e);
        }
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<Courses> updateCourse(@PathVariable  long courseId, @RequestBody Courses course){
        try {
            logger.info("Updating course with ID: {}", courseId);
            Courses updatedCourse = courseService.updateCourse(courseId,course);
            return new ResponseEntity<>(updatedCourse,HttpStatus.OK);
        }catch (CourseNotFoundException e){
            logger.info("Course not found with id: {}",courseId);
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.info("Error updating course with id: {}",courseId,e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses/name/{courseName}")
    public ResponseEntity<Courses> findCourseByName(@PathVariable String courseName){
        logger.info("Fetching course with courseName: {}", courseName);
        try {
            Courses foundCourse = this.courseService.getCourseByName(courseName);
            if (foundCourse!=null){
                return new ResponseEntity<>(foundCourse,HttpStatus.OK);
            }else {
                logger.info("Course not found with name: {}",courseName);
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            logger.info("Error fetching course with name: {}",courseName,e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.restapi.practice.services;

import com.restapi.practice.DAO.CourseDAO;
import com.restapi.practice.entity.Courses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseDAO courseDAO;
    //private  List<Courses> courseList =new ArrayList<>();
    public CourseServiceImpl(CourseDAO courseDAO){
        this.courseDAO = courseDAO;

//        courseList= new ArrayList<>();
//        courseList.add(new Courses(1,"Java","practicing java"));
//        courseList.add(new Courses(2,"spring boot","creating rest api using spring boot"));

    }
    @Override
    public List<Courses> getCourses() {
        //return courseList;
        return courseDAO.findAll();
    }

    @Override
    public Courses getCourse(long courseId) {
//        Courses c = null;
//        for(Courses courses :courseList){
//            if(courses.getId()==courseId){
//                c= courses;
//                break;
//            }
//        }
//        return c;
        return courseDAO.findById(courseId);
    }

    @Override
    public Courses addCourse(Courses course) {
//        courseList.add(course);
//        return course;
        return courseDAO.save(course);
    }

    @Override
    public boolean deleteCourse(long courseId) {

//        for(Courses courses:courseList){
//            if (courses.getId()==courseId){
//                courseList.remove(courses);
//            }
//        }
//        return courseList.removeIf(courses -> courses.getId() == courseId);


        if (courseDAO.existsById(courseId)) {
            courseDAO.deleteById(courseId);
            return true;
        }
        return false;
    }

    @Override
    public Courses updateCourse(long courseId, Courses courses) {
//        for(Courses existingCourse:courseList){
//            if(existingCourse.getId()==courseId){
//                existingCourse.setTitle(courses.getTitle());
//                existingCourse.setDescription(courses.getDescription());
//                return existingCourse;
//            }
//        }
//        return null;

        if(courseDAO.existsById(courseId)){
            return courseDAO.save(courses);
        }
        return null;
    }

    @Override
    public Courses getCourseByName(String  courseName) {
//        Courses c = null;
//        for(Courses existingCourse: courseList){
//            if(existingCourse.getTitle().equalsIgnoreCase(courseName)){
//                c= existingCourse;
//                break;
//            }
//        }
//        return  c;
        return courseDAO.findByTitleIgnoreCase(courseName);
    }
}

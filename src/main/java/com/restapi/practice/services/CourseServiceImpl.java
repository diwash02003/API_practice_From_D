package com.restapi.practice.services;

import com.restapi.practice.entity.Courses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private  List<Courses> courseList =new ArrayList<>();
    public CourseServiceImpl(){
        courseList= new ArrayList<>();
        courseList.add(new Courses(1,"Java","practicing java"));
        courseList.add(new Courses(2,"spring boot","creating rest api using spring boot"));
    }
    @Override
    public List<Courses> getCourses() {
        return courseList;
    }

    @Override
    public Courses getCourse(long courseId) {
        Courses c = null;
        for(Courses courses :courseList){
            if(courses.getId()==courseId){
                c= courses;
                break;
            }
        }
        return c;
    }

    @Override
    public Courses addCourse(Courses course) {
        courseList.add(course);
        return course;
    }

    @Override
    public boolean deleteCourse(long courseId) {

//        for(Courses courses:courseList){
//            if (courses.getId()==courseId){
//                courseList.remove(courses);
//            }
//        }
        return courseList.removeIf(courses -> courses.getId() == courseId);
    }

    @Override
    public Courses updateCourse(long courseId, Courses courses) {
        for(Courses existingCourse:courseList){
            if(existingCourse.getId()==courseId){
                existingCourse.setTitle(courses.getTitle());
                existingCourse.setDescription(courses.getDescription());
                return existingCourse;
            }
        }
        return null;
    }

    @Override
    public Courses getCourseByName(String  courseName) {
        Courses c = null;
        for(Courses existingCourse: courseList){
            if(existingCourse.getTitle().equalsIgnoreCase(courseName)){
                c= existingCourse;
                break;
            }
        }
        return  c;
    }
}

package com.restapi.practice.services;

import com.restapi.practice.entity.Courses;

import java.util.List;

public interface CourseService {
    List<Courses> getCourses() ;
    Courses getCourse(long courseId);
    Courses addCourse(Courses courses);
    boolean deleteCourse(long courseId);
    Courses updateCourse(long courseId,Courses courses);
    Courses getCourseByName (String courseName);
}


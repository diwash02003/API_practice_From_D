package com.restapi.practice.DAO;

import com.restapi.practice.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDAO extends JpaRepository<Courses,Long> {
    Courses findById(long id);
    void deleteById(long id);
    Courses findByTitleIgnoreCase(String name);
}

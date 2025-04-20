package main_package.repository;

import main_package.entity.Course;
import main_package.entity.CourseData;
import main_package.exception.CourseNotFoundException;
import main_package.exception.CoursesNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface CourseRepository extends JpaRepository<Course, Long> {
  ArrayList<CourseData> getAllCoursesById(Long id) throws CoursesNotFoundException;
}

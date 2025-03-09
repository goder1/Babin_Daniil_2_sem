package main_package.repository;

import main_package.entity.CourseData;
import main_package.exception.CourseNotFoundException;
import main_package.exception.CoursesNotFoundException;

import java.util.ArrayList;

public interface CourseRepository {
  ArrayList<CourseData> getAllCoursesById(Long id) throws CoursesNotFoundException;
  Long createCourse(CourseData course);
  CourseData modifyCourseById(Long userId, Long courseId, CourseData courseData) throws CourseNotFoundException;
  CourseData deleteCourseById(Long userId, Long courseId) throws CourseNotFoundException;
}

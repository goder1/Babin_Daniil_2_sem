package main_package.repository;

import main_package.entity.CourseData;

import java.util.ArrayList;

public interface CourseRepository {
  ArrayList<CourseData> getAllCoursesById(Long id);

  Long createCourse(CourseData course);
}

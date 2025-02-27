package main_package.repository;

import main_package.entity.CourseData;
import main_package.exception.CourseNotFoundException;
import main_package.exception.CoursesNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InMemoryCourseRepository implements CourseRepository{
  @Override
  public ArrayList<CourseData> getAllCoursesById(Long id) {
    if (id > 2) {
      throw new CoursesNotFoundException();
    }
    ArrayList<CourseData> temp = new ArrayList<>();
    temp.add(new CourseData("Java Backend developer pro master"));
    temp.add(new CourseData("Spring Boot conquering"));
    return temp;
  }

  @Override
  public CourseData modifyCourseById(Long userId, Long courseId, CourseData courseData) throws CourseNotFoundException{
    if (courseId == 1 || courseId == 2) {
      return new CourseData("How to master java in one hour guide");
    } else {
      throw new CourseNotFoundException();
    }
  }

  @Override
  public CourseData deleteCourseById(Long userId, Long courseId) throws CourseNotFoundException {
    if (courseId == 1 || courseId == 2) {
      return new CourseData("Spring in previous centuries(course version)?(deleted)");
    } else {
      throw new CourseNotFoundException();
    }
  }

  @Override
  public Long createCourse(CourseData course) {
    return 3L;
  }
}

package main_package.repository;

import main_package.entity.CourseData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InMemoryCourseRepository implements CourseRepository{
  @Override
  public ArrayList<CourseData> getAllCoursesById(Long id) {
    ArrayList<CourseData> temp = new ArrayList<>();
    temp.add(new CourseData("Java Backend developer pro master"));
    temp.add(new CourseData("Spring Boot conquering"));
    return temp;
  }
  @Override
  public Long createCourse(CourseData course) {
    return 3L;
  }
}

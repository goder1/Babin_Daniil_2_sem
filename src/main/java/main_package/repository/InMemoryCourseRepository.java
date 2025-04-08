package main_package.repository;

import lombok.RequiredArgsConstructor;
import main_package.entity.CourseData;
import main_package.exception.CourseNotFoundException;
import main_package.exception.CoursesNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class InMemoryCourseRepository implements CourseRepository{
  private final RestTemplate restTemplate;
  private final WebClient webClient;

  @Override
  public ArrayList<CourseData> getAllCoursesById(Long id) {
    if (id > 2) {
      throw new CoursesNotFoundException();
    }
    ArrayList<CourseData> temp = new ArrayList<>();
    temp.add(new CourseData("Java Backend developer pro master"));
    temp.add(new CourseData("Spring Boot conquering"));

    restTemplate.getForEntity("https://github.com/sooren0936/MIPT/blob/master/java/second_semester", String.class);

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
    webClient
        .get()
        .uri("https://github.com/sooren0936/MIPT/blob/master/java/second_semester")
        .retrieve()
        .bodyToMono(String.class)
        .block();

    return 3L;
  }

  @Override
  public boolean findCourseById(Long userId, Long courseId) {
    if (courseId >= 0) {
      return true;
    }
    return false;
  }
}

package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.entity.CourseData;
import main_package.entity.CourseData;
import main_package.entity.CourseData;
import main_package.repository.CourseRepository;
import main_package.request.CourseCreateRequest;
import main_package.request.CourseCreateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class CourseService {
  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Long addCourse(CourseCreateRequest request) {
    log.info("Creating new course with name: {}", request.name());
    Long courseId = courseRepository.createCourse(new CourseData(request.name()));
    log.info("Created new course with id: {}", courseId);
    return courseId;
  }

  public ArrayList<CourseData> getAllCoursesById(Long id) {
    log.info("Getting courses with user id: {}", id);
    ArrayList<CourseData> courses = courseRepository.getAllCoursesById(id);
    log.info("Found courses with user id: {}", id);
    return courses;
  }

  public CourseData modifyCourseById(Long userId, Long courseId, CourseCreateRequest request) {
    log.info("Modifying course with user_id: {} and course_id: {}", userId, courseId);
    CourseData newCourse = courseRepository.modifyCourseById(userId, courseId, new CourseData(request.name()));
    log.info("Modified user course with user_id: {} and course_id: {}", userId, courseId);
    return newCourse;
  }

//  Удаление курса должно произойти и произойти ровно один раз, чтобы
//  у пользователя не снимались деньги за курс, от которого он отписался (например)
  public CourseData deleteCourseById(Long userId, Long courseId) {
    log.info("Deleting course with user_id: {} and course_id: {}", userId, courseId);
    Long temp = courseId;
    CourseData oldCourse = null;
    while (courseRepository.findCourseById(userId, temp)) {
      oldCourse = courseRepository.deleteCourseById(userId, temp);
      temp = -1L;
    }
    log.info("Deleted user course with user_id: {} and course_id: {}", userId, courseId);
    return oldCourse;
  }
}

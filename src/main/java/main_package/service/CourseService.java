package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.entity.Course;
import main_package.entity.CourseData;
import main_package.entity.CourseData;
import main_package.entity.CourseData;
import main_package.repository.CourseRepository;
import main_package.request.CourseCreateRequest;
import main_package.request.CourseCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class CourseService {
  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @Transactional
  public Long addCourse(CourseCreateRequest request) {
    log.info("Creating new course with name: {}", request.name());
    Course course = courseRepository.save(new Course(null, request.name()));
    log.info("Created new course with id: {}", course.getId());
    return course.getId();
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public List<Course> getAllCoursesById(Long id) {
    log.info("Getting courses with user id: {}", id);
    List<Course> courses = courseRepository.findAllById(Collections.singleton(id));
    log.info("Found courses with user id: {}", id);
    return courses;
  }

  @Transactional
  public Course modifyCourseById(Long userId, Long courseId, CourseCreateRequest request) {
    log.info("Modifying course with user_id: {} and course_id: {}", userId, courseId);
    Course newCourse = courseRepository.save(new Course(courseId, request.name()));
    log.info("Modified user course with user_id: {} and course_id: {}", userId, courseId);
    return newCourse;
  }

//  Удаление курса должно произойти и произойти ровно один раз, чтобы
//  у пользователя не снимались деньги за курс, от которого он отписался (например)
  @Transactional
  public CourseData deleteCourseById(Long userId, Long courseId) {
    log.info("Deleting course with user_id: {} and course_id: {}", userId, courseId);
    Long temp = courseId;
    CourseData oldCourse = null;
    while (courseRepository.findById(temp).isPresent()) {
      courseRepository.deleteById(temp);
      temp = -1L;
    }
    log.info("Deleted course with course_id: {}", courseId);
    return oldCourse;
  }
}

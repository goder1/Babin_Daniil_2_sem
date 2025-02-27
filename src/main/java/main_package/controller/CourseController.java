package main_package.controller;

import main_package.entity.CourseData;
import main_package.request.CourseCreateRequest;
import main_package.response.CourseDeleteResponse;
import main_package.response.CoursePatchResponse;
import main_package.response.CourseGetResponse;
import main_package.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/{userId}")
public class CourseController implements CourseControllerInterface{
  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @Override
  public ResponseEntity<List<CourseGetResponse>> getAllCoursesByUserId(Long userId) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(courseService.getAllCoursesById(userId).stream().map(courseData -> new CourseGetResponse(courseData.name())).collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<List<CourseGetResponse>> addCourseToUserById(Long userId, CourseCreateRequest Course) {
    courseService.addCourse(Course);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<CoursePatchResponse> modifyCourseByUserId(Long userId, Long courseId, CourseCreateRequest course) {
    CourseData newCourse = courseService.modifyCourseById(userId, courseId, course);
    return ResponseEntity.status(HttpStatus.OK).body(new CoursePatchResponse(newCourse.name()));
  }

  @Override
  public ResponseEntity<CourseDeleteResponse> deleteCourseByUserId(Long userId, Long courseId) {
    CourseData oldCourse = courseService.deleteCourseById(userId, courseId);
    return ResponseEntity.status(HttpStatus.OK).body(new CourseDeleteResponse(oldCourse.name()));
  }
}

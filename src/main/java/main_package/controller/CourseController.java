package main_package.controller;

import main_package.request.CourseCreateRequest;
import main_package.response.CourseGetResponse;
import main_package.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/course")
public class CourseController {
  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<CourseGetResponse>> getAllCoursesByUserId(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(courseService.getAllCoursesById(id).stream().map(courseData -> new CourseGetResponse(courseData.name())).collect(Collectors.toList()));
  }
  @PutMapping("/user/{id}")
  public ResponseEntity<List<CourseGetResponse>> addCourseToUserById(@PathVariable Long id, @RequestBody CourseCreateRequest Course) {
    courseService.addCourse(Course);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}

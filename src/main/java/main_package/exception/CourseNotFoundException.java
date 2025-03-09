package main_package.exception;

public class CourseNotFoundException extends RuntimeException {
  public CourseNotFoundException() {
    super("Course not found :(");
  }
}

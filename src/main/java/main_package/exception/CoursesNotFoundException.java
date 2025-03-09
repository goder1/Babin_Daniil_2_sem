package main_package.exception;

public class CoursesNotFoundException extends RuntimeException {
  public CoursesNotFoundException() {
    super("Courses not found :(");
  }
}

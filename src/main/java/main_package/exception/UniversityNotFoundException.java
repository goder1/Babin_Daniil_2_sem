package main_package.exception;

public class UniversityNotFoundException extends RuntimeException {
  public UniversityNotFoundException() {
    super("University not found :(");
  }
}

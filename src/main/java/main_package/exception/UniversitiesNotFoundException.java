package main_package.exception;

public class UniversitiesNotFoundException extends RuntimeException {
  public UniversitiesNotFoundException() {
    super("Universities not found :(");
  }
}

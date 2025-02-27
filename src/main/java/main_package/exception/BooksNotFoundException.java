package main_package.exception;

public class BooksNotFoundException extends RuntimeException {
  public BooksNotFoundException() {
    super("Books not found :(");
  }
}

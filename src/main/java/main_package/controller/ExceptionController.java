package main_package.controller;

import main_package.exception.BookNotFoundException;
import main_package.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

//  @ExceptionHandler(UserNotFoundException.class)
//  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//  }
}

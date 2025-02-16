package main_package.controller;

import main_package.request.BookCreateRequest;
import main_package.response.BookGetResponse;
import main_package.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<BookGetResponse>> getAllBooksByUserId(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(bookService.getAllBooksById(id).stream().map(bookData -> new BookGetResponse(bookData.name(), bookData.pages(), bookData.author())).collect(Collectors.toList()));
  }
  @PutMapping("/user/{id}")
  public ResponseEntity<List<BookGetResponse>> addBookToUserById(@PathVariable Long id, @RequestBody BookCreateRequest book) {
    bookService.addBook(book);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}

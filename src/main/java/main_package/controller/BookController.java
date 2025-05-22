package main_package.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import main_package.entity.Book;
import main_package.request.BookCreateRequest;
import main_package.response.BookDeleteResponse;
import main_package.response.BookGetResponse;
import main_package.response.BookPatchResponse;
import main_package.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/book")
public class BookController implements BookControllerInterface {
  private final BookService bookService;
  private final CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("BookControllerCircuitBreaker");

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @Override
  public ResponseEntity<List<BookGetResponse>> getAllBooksByUserId(Long userId) {
    return circuitBreaker.executeSupplier(() -> {
      return ResponseEntity.status(HttpStatus.OK)
          .body(bookService.getAllBooksByUserId(userId).stream().map(bookData -> new BookGetResponse(bookData.getName(), bookData.getPages(), bookData.getAuthor())).collect(Collectors.toList()));
    });
  }

  @Override
  public ResponseEntity<Void> addBook(BookCreateRequest book) {
    return circuitBreaker.executeSupplier(() -> {
      bookService.addBook(book);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    });
  }

  @Override
  public ResponseEntity<BookPatchResponse> modifyBookById(Long bookId, BookCreateRequest book) {
    return circuitBreaker.executeSupplier(() -> {
      Book newBook = bookService.modifyBookById(bookId, book);
      return ResponseEntity.status(HttpStatus.OK).body(new BookPatchResponse(newBook.getName(), newBook.getPages(), newBook.getAuthor()));
    });
  }

  @Override
  public ResponseEntity<BookDeleteResponse> deleteBookById(Long bookId) {
    return circuitBreaker.executeSupplier(() -> {
      Book oldBook = bookService.deleteBookById(bookId);
      log.info(oldBook.toString());

      return ResponseEntity.status(HttpStatus.OK).body(new BookDeleteResponse(oldBook.getName(), oldBook.getPages(), oldBook.getAuthor()));
    });
  }

  @Override
  public ResponseEntity<BookGetResponse> getBookById(Long bookId) {
    return circuitBreaker.executeSupplier(() -> {
      Book book = bookService.getBookById(bookId);
      //log.info(book.toString() + "??????????????");
      return ResponseEntity.status(HttpStatus.OK)
          .body(new BookGetResponse("Dan", 10L, "dan"));
      //.body(new BookGetResponse(book.name(), book.pages(), book.author()));
    });
  }
}
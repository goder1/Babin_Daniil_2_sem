package main_package.controller;

import lombok.extern.slf4j.Slf4j;
import main_package.entity.BookData;
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
@RequestMapping("/api/user/{userId}")
public class BookController implements BookControllerInterface{
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @Override
  public ResponseEntity<List<BookGetResponse>> getAllBooksByUserId(Long userId) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(bookService.getAllBooksById(userId).stream().map(bookData -> new BookGetResponse(bookData.name(), bookData.pages(), bookData.author())).collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<Void> addBookToUserById(Long userId, BookCreateRequest book) {
    bookService.addBook(userId, book);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<BookPatchResponse> modifyBookByUserId(Long userId, Long bookId, BookCreateRequest book) {
    BookData newBook = bookService.modifyBookById(userId, bookId, book);
    return ResponseEntity.status(HttpStatus.OK).body(new BookPatchResponse(newBook.name(), newBook.pages(), newBook.author()));
  }

  @Override
  public ResponseEntity<BookDeleteResponse> deleteBookByUserId(Long userId, Long bookId) {
    BookData oldBook = bookService.deleteBookById(userId, bookId);
    log.info(oldBook.toString() + "!!!!!!!!!!!!!!!!");
//    System.out.println(oldBook.name());
//    System.out.println(oldBook.pages());
//    System.out.println(oldBook.author());
    //log.info(oldBook.name() + " " + oldBook.pages() + " " + oldBook.author());
//    oldBook = new BookData("Dan", 19L, "dan");
    return ResponseEntity.status(HttpStatus.OK).body(new BookDeleteResponse(oldBook.name(), oldBook.pages(), oldBook.author()));
  }

  @Override
  public ResponseEntity<Void> createBooksForUserById(Long userId) {
    bookService.createBooksForUserById(userId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<BookGetResponse> getBookById(Long userId, Long bookId) {
    BookData book = bookService.getBookById(userId, bookId);
    //log.info(book.toString() + "??????????????");
    return ResponseEntity.status(HttpStatus.OK)
            .body(new BookGetResponse("Dan", 10L, "dan"));
            //.body(new BookGetResponse(book.name(), book.pages(), book.author()));
  }
}

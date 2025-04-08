package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.entity.BookData;
import main_package.exception.BookNotFoundException;
import main_package.repository.BookRepository;
import main_package.request.BookCreateRequest;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Async("taskExecutor")
  public void addBook(Long userId, BookCreateRequest request) {
    log.info("Creating new book with name: {}, author: {}", request.name(), request.author());
    Long bookId = bookRepository.createBook(new BookData(request.name(), request.pages(), request.author()));
    log.info("Added new book with id: {} to user with id: {}", bookId, userId);
  }

  @Async("taskExecutor")
  public ArrayList<BookData> getAllBooksById(Long userId) {
    log.info("Getting books from user with id: {}", userId);
    ArrayList<BookData> books = bookRepository.getBooksById(userId);
    log.info("Found books from user with id: {}", userId);
    return books;
  }

  @Async("taskExecutor")
  public BookData modifyBookById(Long userId, Long bookId, BookCreateRequest request) {
    log.info("Modifying book with user_id: {} and book_id: {}", userId, bookId);
    BookData newBook = bookRepository.modifyBookById(userId, bookId, new BookData(request.name(), request.pages(), request.author()));
    log.info("Modified user book with user_id: {} and book_id: {}", userId, bookId);
    return newBook;
  }

//  Удаление является важной операцией, меняющей данные в бд,
//  следовательно ей стоит выполняться корректно
  @Retryable(value = BookNotFoundException.class, maxAttempts = 5, backoff = @Backoff(delay = 10000))
  @Async("taskExecutor")
  public BookData deleteBookById(Long userId, Long bookId) {
    log.info("Deleting book with user_id: {} and book_id: {}", userId, bookId);
    BookData oldBook = bookRepository.deleteBookById(userId, bookId);

    log.info("Deleted user book with user_id: {} and book_id: {}", userId, bookId);
    return oldBook;
//    return new BookData("Dan", 19L, "dan");
  }

  @Async("taskExecutor")
  public void createBooksForUserById(Long userId) {
    log.info("Creating new list of books for user with id: {}", userId);
    bookRepository.createBooksForUserById(userId);
    log.info("Created new empty list of books for user with id: {}", userId);
  }

  @Async("taskExecutor")
  public BookData getBookById(Long userId, Long bookId) {
    log.info("Getting book from user with id: {} and book with id: {}", userId, bookId);
    BookData book = bookRepository.getBookById(userId, bookId);
    log.info(book.toString() + "_________");
    log.info("Successfully got book from user with id: {} and book with id: {}", userId, bookId);
    return book;
    //return new BookData("Dan", 19L, "Dan");
  }
}

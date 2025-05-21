package main_package.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main_package.entity.Book;
import main_package.exception.BookNotFoundException;
import main_package.repository.BookRepository;
import main_package.request.BookCreateRequest;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
  private final BookRepository bookRepository;

  @Transactional
  @Async("taskExecutor")
  public void addBook(Long userId, BookCreateRequest request) {
    log.info("Creating new book with name: {}, author: {}", request.name(), request.author());
    Book book = bookRepository.save(new Book(null, request.name(), request.pages(), request.author()));
    log.info("Added new book with id: {} to user with id: {}", book.getId(), userId);
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  @Async("taskExecutor")
  public List<Book> getAllBooksById(Long userId) {
    log.info("Getting books from user with id: {}", userId);
    List<Book> books = bookRepository.findAllById(Collections.singleton(userId));
    log.info("Found books from user with id: {}", userId);
    return books;
  }

  @Transactional
  @Async("taskExecutor")
  public Book modifyBookById(Long bookId, BookCreateRequest request) {
    log.info("Modifying book with id: {}", bookId);
    Book newBook = bookRepository.save(new Book(bookId, request.name(), request.pages(), request.author()));
    log.info("Modified user book with id: {}", bookId);
    return newBook;
  }

//  Удаление является важной операцией, меняющей данные в бд,
//  следовательно ей стоит выполняться корректно
  @Transactional
  @Retryable(value = BookNotFoundException.class, maxAttempts = 5, backoff = @Backoff(delay = 10000))
  @Async("taskExecutor")
  public Book deleteBookById(Long bookId) {
    log.info("Deleting book with book_id: {}", bookId);
    Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    bookRepository.deleteById(bookId);

    log.info("Deleted user book with book_id: {}", bookId);
    return book;
//    return new BookData("Dan", 19L, "dan");
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  @Async("taskExecutor")
  public Book getBookById(Long bookId) {
    log.info("Getting book with id: {}", bookId);
    Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    log.info(book.toString() + "_________");
    log.info("Successfully got book with id: {}", bookId);
    return book;
    //return new BookData("Dan", 19L, "Dan");
  }
}

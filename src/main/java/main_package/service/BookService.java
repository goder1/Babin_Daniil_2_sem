package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.entity.BookData;
import main_package.repository.BookRepository;
import main_package.request.BookCreateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public void addBook(BookCreateRequest request) {
    log.info("Creating new book with name: {}, author: {}", request.name(), request.author());
    Long bookId = bookRepository.createBook(new BookData(request.name(), request.pages(), request.author()));
    log.info("Created new book with id: {}", bookId);
  }

  public ArrayList<BookData> getAllBooksById(Long id) {
    log.info("Getting books with user id: {}", id);
    ArrayList<BookData> books = bookRepository.getBooksById(id);
    log.info("Found book with user id: {}", id);
    return books;
  }
}

package main_package.repository;

import main_package.entity.BookData;
import main_package.exception.BookNotFoundException;
import main_package.exception.BooksNotFoundException;

import java.util.ArrayList;

public interface BookRepository {
  ArrayList<BookData> getBooksById(Long id) throws BooksNotFoundException;
  Long createBook(BookData book);
  BookData modifyBookById(Long userId, Long bookId, BookData bookData) throws BookNotFoundException;
  BookData deleteBookById(Long userId, Long bookId) throws BookNotFoundException;
  void createBooksForUserById(Long userId);
  BookData getBookById(Long userId, Long bookId) throws BookNotFoundException;
}

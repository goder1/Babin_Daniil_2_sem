package main_package.repository;

import main_package.entity.Book;
import main_package.entity.BookData;
import main_package.exception.BookNotFoundException;
import main_package.exception.BooksNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface BookRepository extends JpaRepository<Book, Long> {
  ArrayList<BookData> getBooksById(Long id) throws BooksNotFoundException;
}

package main_package.repository;

import main_package.entity.BookData;

import java.util.ArrayList;

public interface BookRepository {
  ArrayList<BookData> getBooksById(Long id);

  Long createBook(BookData book);
}

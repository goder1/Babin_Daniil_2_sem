package main_package.repository;

import main_package.entity.BookData;
import main_package.exception.BookNotFoundException;
import main_package.exception.BooksNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InMemoryBookRepository implements BookRepository{
  @Override
  public ArrayList<BookData> getBooksById(Long userId) throws BooksNotFoundException{
    if (userId > 2) {
      throw new BooksNotFoundException();
    }
    ArrayList<BookData> temp = new ArrayList<>();
    temp.add(new BookData("Spring Top 100 tricks", 666L, "Dmitriy Bobryakov"));
    temp.add(new BookData("MIPT BOT culture", 1000L, "Any_mipt_student"));
    return temp;
  }

  @Override
  public BookData modifyBookById(Long userId, Long bookId, BookData bookData) throws BookNotFoundException{
    if (bookId == 1 || bookId == 2) {
      return new BookData("Spring Top 100 tricks", 666L, "Dmitriy Bobryakov");
    } else {
      throw new BookNotFoundException();
    }
  }

  @Override
  public BookData deleteBookById(Long userId, Long bookId) throws BookNotFoundException{
    if (bookId == 1L || bookId == 2L) {
      return new BookData("Spring in previous centuries?(deleted)", 0L, "First java developers");
    } else {
      throw new BookNotFoundException();
    }
  }

  @Override
  public Long createBook(BookData book) {
    return 3L;
  }

  @Override
  public void createBooksForUserById(Long userId) {
    ArrayList<BookData> data = new ArrayList<>();
  }

  @Override
  public BookData getBookById(Long userId, Long bookId) throws BookNotFoundException{
    if (bookId > 2) {
      throw new BookNotFoundException();
    }
    return new BookData("MIPT BOT culture", 1000L, "Any_mipt_student");
  }
}

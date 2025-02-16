package main_package.repository;

import main_package.entity.BookData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InMemoryBookRepository implements BookRepository{
  @Override
  public ArrayList<BookData> getBooksById(Long id) {
    ArrayList<BookData> temp = new ArrayList<>();
    temp.add(new BookData("Spring Top 100 tricks", 666L, "Dmitriy Bobryakov"));
    temp.add(new BookData("MIPT BOT culture", 1000L, "Any_mipt_student"));
    return temp;
  }

  @Override
  public Long createBook(BookData book) {
    return 3L;
  }
}

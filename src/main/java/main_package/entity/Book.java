package main_package.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "books")
@Schema(name = "Book", description = "Сущность книги")
@Data
@AllArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  String name;

  @NotNull
  Long pages;

  @NotNull
  String author;

  protected Book() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Book book)) {
      return false;
    }
    return id != null && id.equals(book.id);
  }

  @Override
  public int hashCode() {
    return Book.class.hashCode();
  }
}

package main_package.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Schema(name = "Book", description = "Модель книги")
@Getter
@Setter
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookId;

  @Column(name = "name")
  @NotNull
  String name;

  @Column(name = "pages")
  @NotNull
  Long pages;

  @Column(name = "author")
  @NotNull
  String author;

//  @Column(name = "user")
  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  User user;

  public Book() {}

  public Book(Long bookId, String name, Long pages, String author, User user) {
    this.bookId = bookId;
    this.name = name;
    this.author = author;
    this.pages = pages;
    this.user = user;
  }

  public Book(Long bookId, String name, Long pages, String author) {
    this.bookId = bookId;
    this.name = name;
    this.author = author;
    this.pages = pages;
    this.user = null;
  }

  public Book(String name, Long pages, String author) {
    this.name = name;
    this.author = author;
    this.pages = pages;
    this.user = null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Book book)) {
      return false;
    }
    return bookId != null && bookId.equals(book.bookId);
  }

  @Override
  public int hashCode() {
    return Book.class.hashCode();
  }
}

package main_package.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Schema(name = "User", description = "Модель пользователя")
@Table(name = "users")
@Entity
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long userId;

  @Column(name = "userName")
  @NotNull
  @JsonProperty("name")
  private String name;

  @Column(name = "userAge")
  @NotNull
  @JsonProperty("age")
  private Long age;

//  @Column(name = "books")
  @OneToMany(mappedBy = "user")
  @NotNull
  private Set<Book> books;

//  @Column(name = "courses")
  @ManyToMany(fetch = FetchType.LAZY)
  @NotNull
  @JoinTable(
      name = "course_and_user",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  private Set<Course> courses;

//  @Column(name = "universities")
  @ManyToMany(fetch = FetchType.LAZY)
  @NotNull
  @JoinTable(
      name = "university_and_user",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "university_id")
  )
  private Set<University> universities;

  public User() {
    this.books = new HashSet<>();
    this.courses = new HashSet<>();
    this.universities = new HashSet<>();
  }

  public User(Long id) {
    this.userId = id;
    this.books = new HashSet<>();
    this.courses = new HashSet<>();
    this.universities = new HashSet<>();
  }

  public User(Long id, String name, Long age) {
    this.userId = id;
    this.name = name;
    this.age = age;
    this.books = new HashSet<>();
    this.courses = new HashSet<>();
    this.universities = new HashSet<>();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User user)) {
      return false;
    }
    return userId != null && userId.equals(user.userId);
  }

  @Override
  public int hashCode() {
    return User.class.hashCode();
  }
}

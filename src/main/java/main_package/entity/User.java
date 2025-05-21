package main_package.entity;

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
  private Long id;

  @Column(name = "userName")
  @NotNull
  private String name;

  @Column(name = "userAge")
  @NotNull
  private Long age;

  @Column(name = "books")
  @OneToMany(mappedBy = "user")
  @NotNull
  private Set<Book> books;

  @Column(name = "courses")
  @ManyToMany(fetch = FetchType.LAZY)
  @NotNull
  @JoinTable(
      name = "course_and_user",
      joinColumns = @JoinColumn(name = "userId"),
      inverseJoinColumns = @JoinColumn(name = "courseId")
  )
  private Set<Course> courses;

  @Column(name = "universities")
  @ManyToMany(fetch = FetchType.LAZY)
  @NotNull
  @JoinTable(
      name = "unviersity_and_user",
      joinColumns = @JoinColumn(name = "userId"),
      inverseJoinColumns = @JoinColumn(name = "universityId")
  )
  private Set<University> universities;

  public User() {
    this.books = new HashSet<>();
    this.courses = new HashSet<>();
    this.universities = new HashSet<>();
  }

  public User(Long id) {
    this.id = id;
    this.books = new HashSet<>();
    this.courses = new HashSet<>();
    this.universities = new HashSet<>();
  }

  public User(Long id, String name, Long age) {
    this.id = id;
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
    return id != null && id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return User.class.hashCode();
  }
}

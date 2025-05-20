package main_package.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;

@Table(name = "users")
@Entity
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private Long age;

  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.PERSIST})
  private ArrayList<Book> books = new ArrayList<>();

  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.PERSIST})
  private ArrayList<Course> courses = new ArrayList<>();

  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.PERSIST})
  private ArrayList<University> universities = new ArrayList<>();

  protected User() {}

  public User(Long id) {
    this.id = id;
  }

  public User(Long id, String name, Long age) {
    this.id = id;
    this.name = name;
    this.age = age;
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

package main_package.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Schema(name = "University", description = "Модель университета")
@Table(name = "universities")
@Entity
@Getter
@Setter
public class University {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long universityId;

  @Column(name = "name")
  @NotNull
  private String name;

  @Column(name = "students")
  @NotNull
  private Long students;

  @Column(name = "location")
  @NotNull
  private String location;

  @Column(name="users")
  @NotNull
  @ManyToMany(mappedBy="universities")
  private Set<User> users;

  public University() {}

  public University(Long universityId, String name, Long students, String location) {
    this.universityId = universityId;
    this.name = name;
    this.students = students;
    this.location = location;
    this.users = new HashSet<>();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof University university)) {
      return false;
    }
    return universityId != null && universityId.equals(university.universityId);
  }

  @Override
  public int hashCode() {
    return University.class.hashCode();
  }
}

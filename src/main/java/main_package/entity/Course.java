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

@Table(name = "courses")
@Entity
@Schema(name = "Course", description = "Модель курса")
@Getter
@Setter
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long courseId;

  @Column(name = "name")
  @NotNull
  String name;

  @Column(name="users")
  @NotNull
  @ManyToMany(mappedBy="courses")
  private Set<User> users;

  public Course() {}

  public Course(Long courseId, String name) {
    this.courseId = courseId;
    this.name = name;
    this.users = new HashSet<>();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Course course)) {
      return false;
    }
    return courseId != null && courseId.equals(course.courseId);
  }

  @Override
  public int hashCode() {
    return Course.class.hashCode();
  }
}

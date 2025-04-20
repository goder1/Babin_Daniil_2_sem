package main_package.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "courses")
@Entity
@Schema(name = "Course", description = "Сущность курса")
@Data
@AllArgsConstructor
public class Course {
  @Id
  private Long id;

  @NotNull
  String name;

  protected Course() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Course course)) {
      return false;
    }
    return id != null && id.equals(course.id);
  }

  @Override
  public int hashCode() {
    return Course.class.hashCode();
  }
}

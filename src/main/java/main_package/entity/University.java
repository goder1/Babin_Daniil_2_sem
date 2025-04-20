package main_package.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "universities")
@Entity
@Data
@AllArgsConstructor
public class University {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private Long students;

  @NotNull
  private String location;

  protected University() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof University university)) {
      return false;
    }
    return id != null && id.equals(university.id);
  }

  @Override
  public int hashCode() {
    return University.class.hashCode();
  }
}

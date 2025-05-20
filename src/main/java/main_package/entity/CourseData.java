package main_package.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;

@Entity
@Schema(name = "CourseData", description = "Сущность реплики курса")
public record CourseData(String name) {
}

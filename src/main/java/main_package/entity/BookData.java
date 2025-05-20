package main_package.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;

@Entity
@Schema(name = "BookData", description = "Сущность реплики книги")
public record BookData(String name, Long pages, String author) {
}

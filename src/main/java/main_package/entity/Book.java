package main_package.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Book", description = "Сущность книги")
public record Book(Long id, String name, Long pages, String author) {
}

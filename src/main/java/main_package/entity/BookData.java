package main_package.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BookData", description = "Сущность реплики книги")
public record BookData(String name, Long pages, String author) {
}

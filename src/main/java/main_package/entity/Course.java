package main_package.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Course", description = "Сущность курса")
public record Course(Long id, String name) {
}

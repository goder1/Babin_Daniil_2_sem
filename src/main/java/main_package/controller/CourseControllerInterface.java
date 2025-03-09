package main_package.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import main_package.exception.CourseNotFoundException;
import main_package.exception.CoursesNotFoundException;
import main_package.request.CourseCreateRequest;
import main_package.response.CourseDeleteResponse;
import main_package.response.CoursePatchResponse;
import main_package.response.CourseGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Course API", description = "Управление курсами пользователя")
public interface CourseControllerInterface {
  @Operation(summary = "Получить курсы пользователя")
  @ApiResponse(responseCode = "200", description = "Курсы найдены")
  @ApiResponse(
          responseCode = "404",
          description = "Курсы не найдены",
          content = @Content(schema = @Schema(implementation = CoursesNotFoundException.class))
  )
  @GetMapping("/course")
  ResponseEntity<List<CourseGetResponse>> getAllCoursesByUserId(@Parameter(description = "ID пользователя") @PathVariable Long userId);

  @Operation(summary = "Добавить курс для пользователя по ID")
  @ApiResponse(responseCode = "201", description = "Курс добавлен")
  @ApiResponse(responseCode = "200", description = "Курс добавлен")
  @PutMapping("/course")
  ResponseEntity<List<CourseGetResponse>> addCourseToUserById(@Parameter(description = "ID пользователя") @PathVariable Long userId, @RequestBody CourseCreateRequest Course);

  @Operation(summary = "Изменить данные курса")
  @ApiResponse(responseCode = "204", description = "Курс изменен")
  @ApiResponse(responseCode = "200", description = "Курс изменен")
  @ApiResponse(
          responseCode = "404",
          description = "Курс не найден",
          content = @Content(schema = @Schema(implementation = CourseNotFoundException.class))
  )
  @PatchMapping("/course/{courseId}")
  ResponseEntity<CoursePatchResponse> modifyCourseByUserId(@Parameter(description = "ID пользователя") @PathVariable Long userId, @Parameter(description = "ID курса") @PathVariable Long courseId, @RequestBody CourseCreateRequest course);

  @Operation(summary = "Удалить курс по ID")
  @ApiResponse(responseCode = "204", description = "Курс удален")
  @ApiResponse(responseCode = "200", description = "Курс удален")
  @ApiResponse(
          responseCode = "404",
          description = "Курс не найден",
          content = @Content(schema = @Schema(implementation = CourseNotFoundException.class))
  )
  @DeleteMapping("/course/{courseId}")
  ResponseEntity<CourseDeleteResponse> deleteCourseByUserId(@Parameter(description = "ID пользователя") @PathVariable Long userId, @Parameter(description = "ID курса") @PathVariable Long courseId);
}

package main_package.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import main_package.exception.UniversitiesNotFoundException;
import main_package.request.UniversityCreateRequest;
import main_package.response.UniversityGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "University API", description = "Управление университетами пользователя")
public interface UniversityControllerInterface {
  @Operation(summary = "Получить университеты пользователя")
  @ApiResponse(
          responseCode = "404",
          description = "Университеты не найдены",
          content = @Content(schema = @Schema(implementation = UniversitiesNotFoundException.class))
  )
  @ApiResponse(responseCode = "200", description = "Унивеситеты найдены")
  @GetMapping("/user/{id}")
  ResponseEntity<List<UniversityGetResponse>> getAllUniversitiesByUserId(@Parameter(description = "ID пользователя") @PathVariable Long id);

  @Operation(summary = "Добавить университет для пользователя по ID")
  @ApiResponse(responseCode = "201", description = "Университет добавлен")
  @ApiResponse(responseCode = "200", description = "Университет добавлен")
  @PutMapping("/user/{id}")
  ResponseEntity<List<UniversityGetResponse>> addUniversityToUserById(@Parameter(description = "ID пользователя") @PathVariable Long id, @RequestBody UniversityCreateRequest university);
}

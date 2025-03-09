package main_package.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import main_package.exception.UserNotFoundException;
import main_package.request.UserCreateRequest;
import main_package.response.UserCreateResponse;
import main_package.response.UserGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User API", description = "Управление пользователями")
public interface UserControllerInterface {
  @Operation(summary = "Создать пользователя")
  @ApiResponse(responseCode = "201", description = "Пользователь создан")
  @ApiResponse(responseCode = "200", description = "Пользователь создан")
  @PostMapping("/")
  ResponseEntity<UserCreateResponse> createUser(@RequestBody UserCreateRequest request);

  @Operation(summary = "Получить пользователя по ID")
  @ApiResponse(responseCode = "200", description = "Пользователь найден")
  @ApiResponse(
          responseCode = "404",
          description = "Пользователь не найден",
          content = @Content(schema = @Schema(implementation = UserNotFoundException.class))
  )
  @GetMapping("/{id}")
  ResponseEntity<UserGetResponse> getUserById(@Parameter(description = "ID пользователя") @PathVariable Long id);
}

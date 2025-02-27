package main_package.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import main_package.exception.BookNotFoundException;
import main_package.exception.BooksNotFoundException;
import main_package.request.BookCreateRequest;
import main_package.response.BookDeleteResponse;
import main_package.response.BookGetResponse;
import main_package.response.BookPatchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book API", description = "Управление книгами пользователя")
public interface BookControllerInterface {
  @Operation(summary = "Получить книги пользователя")
  @ApiResponse(responseCode = "200", description = "Книги найдены")
  @ApiResponse(
          responseCode = "404",
          description = "Книги не найдены",
          content = @Content(schema = @Schema(implementation = BooksNotFoundException.class))
  )
  @GetMapping("/book")
  ResponseEntity<List<BookGetResponse>> getAllBooksByUserId(@Parameter(description = "ID пользователя") @PathVariable Long userId);

  @Operation(summary = "Добавить книгу для пользователя по ID")
  @ApiResponse(responseCode = "201", description = "Книга добавлена")
  @ApiResponse(responseCode = "200", description = "Книга добавлена")
  @PutMapping("/book")
  ResponseEntity<Void> addBookToUserById(@Parameter(description = "ID пользователя") @PathVariable Long userId, @RequestBody BookCreateRequest book);


  @Operation(summary = "Изменить данные книги")
  @ApiResponse(responseCode = "204", description = "Книга изменена")
  @ApiResponse(responseCode = "200", description = "Книга изменена")
  @ApiResponse(
          responseCode = "404",
          description = "Книга не найдена",
          content = @Content(schema = @Schema(implementation = BookNotFoundException.class))
  )
  @PatchMapping("/book/{bookId}")
  ResponseEntity<BookPatchResponse> modifyBookByUserId(@Parameter(description = "ID пользователя") @PathVariable Long userId, @Parameter(description = "ID книги") @PathVariable Long bookId, @RequestBody BookCreateRequest book);

  @Operation(summary = "Удалить книгу по ID")
  @ApiResponse(responseCode = "204", description = "Книга удалена")
  @ApiResponse(responseCode = "200", description = "Книга удалена")
  @ApiResponse(
          responseCode = "404",
          description = "Книга не найдена",
          content = @Content(schema = @Schema(implementation = BookNotFoundException.class))
  )
  @DeleteMapping("/book/{bookId}")
  ResponseEntity<BookDeleteResponse> deleteBookByUserId(@Parameter(description = "ID пользователя") @PathVariable Long userId, @Parameter(description = "ID книги") @PathVariable Long bookId);

  @Operation(summary = "Создать пустой список книг для пользователя")
  @ApiResponse(responseCode = "201", description = "Список книг создан")
  @ApiResponse(responseCode = "200", description = "Список книг создан")
  @PostMapping("/book")
  ResponseEntity<Void> createBooksForUserById(@Parameter(description = "ID пользователя") @PathVariable Long userId);

  @Operation(summary = "Получить книгу пользователя")
  @ApiResponse(responseCode = "200", description = "Книга найдена")
  @ApiResponse(
          responseCode = "404",
          description = "Книга не найдена",
          content = @Content(schema = @Schema(implementation = BookNotFoundException.class))
  )
  @GetMapping("/book/{bookId}")
  ResponseEntity<BookGetResponse> getBookById(@Parameter(description = "ID пользователя") @PathVariable Long userId, @Parameter(description = "ID книги") @PathVariable Long bookId);
}

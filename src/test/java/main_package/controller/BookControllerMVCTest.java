package main_package.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main_package.entity.Book;
import main_package.entity.BookData;
import main_package.exception.BookNotFoundException;
import main_package.request.BookCreateRequest;
import main_package.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = BookController.class,
        excludeAutoConfiguration = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class}
)
public class BookControllerMVCTest {
  private static final String basePath = "/api/user/1/book";
  private static final MediaType jsonContentType = MediaType.valueOf("application/json");

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private BookService bookService;


  @Test
  public void testGetBookSuccess() throws Exception {
    BookData book = new BookData("Dan", 10L, "dan");
    when(bookService.getBookById(1L, 1L))
            .thenReturn(book);

    mockMvc.perform(get(basePath + "/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(jsonContentType))
            .andExpect(jsonPath("$.name").value("Dan"))
            .andExpect(jsonPath("$.pages").value(10L))
            .andExpect(jsonPath("$.author").value("dan"));
    verify(bookService).getBookById(1L, 1L);
  }

  @Test
  public void testDeleteBookSuccess() throws Exception {
    BookData book = new BookData("Dan", 19L, "dan");

    when(bookService.deleteBookById(1L, 0L))
            .thenReturn(book);

    mockMvc.perform(delete(basePath + "/" + 0))
            .andExpect(status().isOk())
            .andExpect(content().contentType(jsonContentType))
            .andExpect(jsonPath("$.name").value("Dan"))
            .andExpect(jsonPath("$.pages").value(19L))
            .andExpect(jsonPath("$.author").value("dan"));
    verify(bookService).deleteBookById(1L, 0L);
  }

  @Test
  public void testPatchBookFail() throws Exception {
    BookCreateRequest patchRequest = new BookCreateRequest("Dan", 19L, "dan");

    when(bookService.modifyBookById(1L, 4L, patchRequest)).thenThrow(BookNotFoundException.class);

    mockMvc.perform(
                    patch(basePath + "/4")
            )
            .andExpect(status().isNotFound());
  }
}

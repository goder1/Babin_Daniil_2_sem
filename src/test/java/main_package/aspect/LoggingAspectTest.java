//package main_package.aspect;
//
//import main_package.Application;
//import main_package.request.BookCreateRequest;
//import main_package.response.BookGetResponse;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("my_app_test")
//class LoggingAspectTest {
//  @Autowired
//  private TestRestTemplate restTemplate;
//
//  @Autowired
//  private LoggingAspect loggingAspect;
//
//  @Test
//  void testBookController() {
//    int currentCnt = loggingAspect.getCnt();
//
//    BookCreateRequest request = new BookCreateRequest("name", 10L, "bombastic");
//    ResponseEntity<Void> response = restTemplate.withBasicAuth("admin", "secret").postForEntity("/api/user/1/book", request, Void.class);
//
//    assertEquals(HttpStatus.CREATED, response.getStatusCode());
////    assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED));
//
//    assertEquals(2L, loggingAspect.getCnt());
//  }
//}
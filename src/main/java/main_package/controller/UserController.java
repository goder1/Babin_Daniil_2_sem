package main_package.controller;

import main_package.entity.UserData;
import main_package.request.UserCreateRequest;
import main_package.response.UserGetResponse;
import main_package.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/")
  public ResponseEntity<Long> createUser(@RequestBody UserCreateRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserGetResponse> getUserById(@PathVariable Long id) {
    UserData user = userService.getUserDataById(id);
    return ResponseEntity.status(HttpStatus.OK).body(new UserGetResponse(user.name(), user.age()));
  }
}

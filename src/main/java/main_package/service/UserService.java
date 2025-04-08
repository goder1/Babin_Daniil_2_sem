package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.entity.UserData;
import main_package.repository.UserRepository;
import main_package.request.UserCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserData createUser(UserCreateRequest request) {
    log.info("Creating new user with name: {}", request.name());
    UserData newUser = userRepository.createUser(new UserData(request.name(), request.age()));
    log.info("Created new user");
    return newUser;
  }

  @Cacheable(value = "users", key = "#userId")
  public UserData getUserDataById(Long userId) {
    log.info("Getting user with id: {}", userId);
    UserData user = userRepository.getUserDataById(userId);
    log.info("Found user with id: {}", userId);
    return user;
  }
}

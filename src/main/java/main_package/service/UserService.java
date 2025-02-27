package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.entity.UserData;
import main_package.repository.UserRepository;
import main_package.request.UserCreateRequest;
import org.springframework.stereotype.Service;

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

  public UserData getUserDataById(Long id) {
    log.info("Getting user with id: {}", id);
    UserData user = userRepository.getUserDataById(id);
    log.info("Found user with id: {}", id);
    return user;
  }
}

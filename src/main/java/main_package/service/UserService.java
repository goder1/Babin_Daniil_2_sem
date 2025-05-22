package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.entity.User;
import main_package.exception.UserNotFoundException;
import main_package.repository.UserRepository;
import main_package.request.UserCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public User createUser(UserCreateRequest request) {
    log.info("Creating new user with name: {}", request.name());
    User newUser = userRepository.save(new User(1L, request.name(), request.age()));
    log.info("Created new user");
    return newUser;
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  @Cacheable(value = "users", key = "#user")
  public User getUserById(Long userId) {
    log.info("Getting user with id: {}", userId);
    User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    log.info("Found user with id: {}", userId);
    return user;
  }
}

package main_package.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main_package.entity.User;
import main_package.entity.UserAction;
import main_package.exception.UserNotFoundException;
import main_package.repository.UserRepository;
import main_package.request.UserCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final KafkaProducerService kafkaService;

  @Transactional
  public User createUser(UserCreateRequest request) throws JsonProcessingException {
    log.info("Creating new user with name: {}", request.name());
    User newUser = userRepository.save(new User(1L, request.name(), request.age()));
    log.info("Created new user");
    kafkaService.sendAction(newUser.getUserId(), new UserAction(UUID.fromString(String.valueOf(newUser.getUserId())), Instant.now(), "CREATE"));
    return newUser;
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  @Cacheable(value = "users", key = "#user")
  public User getUserById(Long userId) throws JsonProcessingException {
    log.info("Getting user with id: {}", userId);
    User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    log.info("Found user with id: {}", userId);
    kafkaService.sendAction(userId, new UserAction(UUID.fromString(String.valueOf(userId)), Instant.now(), "READ"));
    return user;
  }
}

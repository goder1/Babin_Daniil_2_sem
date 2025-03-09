package main_package.controller.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Endpoint(id = "uuid")
public class RandomUUIDController {

  @ReadOperation
  public UUID randomUUID() {
    return UUID.randomUUID();
  }
}

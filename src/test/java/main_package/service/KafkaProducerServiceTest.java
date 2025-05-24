package main_package.service;

import main_package.entity.UserAction;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
    classes = {KafkaProducerService.class},
    properties = {"topic-to-send-message=my-topic"}
)
@Import({KafkaAutoConfiguration.class, KafkaProducerServiceTest.ObjectMapperTestConfig.class})
@Testcontainers
class KafkaProducerServiceTest {

  @TestConfiguration
  static class ObjectMapperTestConfig {
    @Bean
    public ObjectMapper objectMapper() {
      return new ObjectMapper();
    }
  }

  @Container
  @ServiceConnection
  public static final KafkaContainer KAFKA = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.0.1"));

  @Autowired
  private KafkaProducerService kafkaProducerService;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldSendMessageToKafkaSuccessfully() {
    UserAction testAction = new UserAction(UUID.randomUUID(), Instant.now(), "CREATE");

    assertDoesNotThrow(() -> kafkaProducerService.sendAction(1L, testAction));

    KafkaTestConsumer consumer = new KafkaTestConsumer(KAFKA.getBootstrapServers(), "my-topic-group");
    consumer.subscribe(List.of("my-topic"));

    ConsumerRecords<String, String> records = consumer.poll();
    assertEquals(1, records.count());
    records.iterator().forEachRemaining(
        record -> {
          UserAction message = null;
          try {
            message = objectMapper.readValue(record.value(), UserAction.class);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          assertEquals(testAction, message);
        }
    );
  }
}
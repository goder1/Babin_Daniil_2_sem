package main_package.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import main_package.entity.Outbox;
import main_package.repository.OutboxRepository;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"topic-to-send-message=my-topic"})
@Import({OutboxService.class, KafkaProducerService.class, KafkaAutoConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("my_app_test")
class OutboxServiceTest {
  @TestConfiguration
  static class ObjectMapperTestConfig {
    @Bean
    public ObjectMapper objectMapper() {
      return new ObjectMapper();
    }
  }

  @Container
  @ServiceConnection
  public static final KafkaContainer KAFKA =
      new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.0.1"));

  @Container
  @ServiceConnection
  public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:13")
      .withDatabaseName("mydb")
      .withUsername("admin")
      .withPassword("secret")
      .withInitScript("init.sql");

  @Autowired
  private OutboxService outboxService;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private OutboxRepository outboxRepository;

  private static KafkaTestConsumer consumer;

  @BeforeAll
  static void setUp() {
    consumer = new KafkaTestConsumer(KAFKA.getBootstrapServers(), "my-topic-group");
    consumer.subscribe(List.of("my-topic"));
  }

  @Test
  void shouldSendMessageToKafkaSuccessfully() {
    outboxRepository.save(new Outbox(1L, "insert"));

    assertDoesNotThrow(() -> outboxService.processOutbox());

    KafkaTestConsumer consumer =
        new KafkaTestConsumer(KAFKA.getBootstrapServers(), "my-topic-group");
    consumer.subscribe(List.of("my-topic"));

    ConsumerRecords<String, String> records = consumer.poll();
    assertEquals(1, records.count());
    records
        .iterator()
        .forEachRemaining(
            record -> {
              String message = null;
              try {
                JsonNode rootNode = objectMapper.readTree(record.value());
                message = objectMapper.treeToValue(rootNode.get("value"), String.class);
                System.out.println(message);
              } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                throw new RuntimeException(e);
              }
              assertEquals("insert", message.split(" ")[1]);
              assertEquals(1L, Long.parseLong(message.split(" ")[0]));
            });
  }

  @Test
  void shouldFailToSendMessage() {
    assertThrows(
        IllegalArgumentException.class, () -> outboxRepository.save(new Outbox(null, null)));
    ConsumerRecords<String, String> records = consumer.poll();
    assertEquals(0, records.count());
  }
}
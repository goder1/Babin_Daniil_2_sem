package main_package.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import main_package.entity.Outbox;
import main_package.repository.OutboxRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboxService {

  private final OutboxRepository outboxRepository;
  private final KafkaProducerService producerService;

  @Transactional
  @Scheduled(fixedDelay = 10000)
  public void processOutbox() throws JsonProcessingException {
    List<Outbox> result = outboxRepository.findAll();
    for (Outbox outbox : result) {
      String[] resultSplit = outbox.getValue().split(":");
      Long id = Long.parseLong(resultSplit[0]);
      String action = resultSplit[1];
      producerService.sendAction(id, action);
    }
    outboxRepository.deleteAll(result);
  }
}
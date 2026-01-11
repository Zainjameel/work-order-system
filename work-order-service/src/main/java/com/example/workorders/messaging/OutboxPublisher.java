package com.example.workorders.messaging;

import com.example.workorders.domain.OutboxEvent;
import com.example.workorders.repo.OutboxRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OutboxPublisher {
  private final OutboxRepository repo;
  private final KafkaTemplate<String, String> kafka;
  private final String topic;

  public OutboxPublisher(OutboxRepository repo,
                         KafkaTemplate<String, String> kafka,
                         @Value("${app.kafka.topic}") String topic) {
    this.repo = repo;
    this.kafka = kafka;
    this.topic = topic;
  }

  @Scheduled(fixedDelay = 1000)
  @Transactional
  public void publish() {
    List<OutboxEvent> batch = repo.findTop50ByStatusOrderByCreatedAtAsc("NEW");
    for (OutboxEvent e : batch) {
      kafka.send(topic, e.getAggregateId(), e.getPayload());
      e.setStatus("SENT");
    }
  }
}
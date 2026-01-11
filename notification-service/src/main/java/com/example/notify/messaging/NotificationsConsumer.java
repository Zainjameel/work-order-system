package com.example.notify.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationsConsumer {

  @KafkaListener(topics = "${app.kafka.topic}", groupId = "notification-service")
  public void onMessage(String payload) {
    System.out.println("NOTIFY EVENT: " + payload);
  }
}
package com.example.reporting.messaging;

import com.example.reporting.domain.WorkOrderReport;
import com.example.reporting.repo.WorkOrderReportRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
public class WorkOrderEventsConsumer {
  private final WorkOrderReportRepository repo;
  private final ObjectMapper mapper = new ObjectMapper();

  public WorkOrderEventsConsumer(WorkOrderReportRepository repo) {
    this.repo = repo;
  }

  @KafkaListener(topics = "${app.kafka.topic}", groupId = "reporting-service")
  @Transactional
  public void onMessage(String payload) throws Exception {
    JsonNode root = mapper.readTree(payload);
    String type = root.get("eventType").asText();
    if (!"WorkOrderCreated".equals(type)) return;

    JsonNode data = root.get("data");
    Long id = data.get("workOrderId").asLong();

    WorkOrderReport r = new WorkOrderReport();
    r.setWorkOrderId(id);
    r.setSite(data.get("site").asText());
    r.setAssetType(data.get("assetType").asText());
    r.setAssetId(data.get("assetId").asText());
    r.setPriority(data.get("priority").asText());
    r.setStatus(data.get("status").asText());
    r.setLastUpdatedAt(Instant.parse(root.get("occurredAt").asText()));

    repo.save(r);
  }
}
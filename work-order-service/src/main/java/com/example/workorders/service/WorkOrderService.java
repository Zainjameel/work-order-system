package com.example.workorders.service;

import com.example.workorders.api.dto.CreateWorkOrderRequest;
import com.example.workorders.domain.OutboxEvent;
import com.example.workorders.domain.WorkOrder;
import com.example.workorders.repo.OutboxRepository;
import com.example.workorders.repo.WorkOrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
public class WorkOrderService {
  private final WorkOrderRepository workOrderRepo;
  private final OutboxRepository outboxRepo;
  private final ObjectMapper mapper = new ObjectMapper();

  public WorkOrderService(WorkOrderRepository workOrderRepo, OutboxRepository outboxRepo) {
    this.workOrderRepo = workOrderRepo;
    this.outboxRepo = outboxRepo;
  }

  @Transactional
  public WorkOrder create(CreateWorkOrderRequest req) throws Exception {
    WorkOrder wo = new WorkOrder();
    wo.setSite(req.site());
    wo.setAssetType(req.assetType());
    wo.setAssetId(req.assetId());
    wo.setPriority(req.priority());
    wo.setDescription(req.description());
    wo.setStatus("OPEN");

    wo = workOrderRepo.save(wo);

    var event = Map.of(
        "eventId", UUID.randomUUID().toString(),
        "eventType", "WorkOrderCreated",
        "occurredAt", Instant.now().toString(),
        "data", Map.of(
            "workOrderId", wo.getWorkOrderId(),
            "site", wo.getSite(),
            "assetType", wo.getAssetType(),
            "assetId", wo.getAssetId(),
            "priority", wo.getPriority(),
            "description", wo.getDescription(),
            "status", wo.getStatus()
        )
    );

    OutboxEvent outbox = new OutboxEvent();
    outbox.setId(UUID.randomUUID().toString());
    outbox.setAggregateType("WORK_ORDER");
    outbox.setAggregateId(String.valueOf(wo.getWorkOrderId()));
    outbox.setEventType("WorkOrderCreated");
    outbox.setPayload(mapper.writeValueAsString(event));
    outbox.setStatus("NEW");

    outboxRepo.save(outbox);
    return wo;
  }
}
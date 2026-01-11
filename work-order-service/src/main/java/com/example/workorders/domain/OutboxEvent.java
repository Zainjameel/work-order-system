package com.example.workorders.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "OUTBOX_EVENTS")
public class OutboxEvent {
  @Id
  private String id; // UUID string

  private String aggregateType;
  private String aggregateId;
  private String eventType;

  @Lob
  private String payload;

  private String status; // NEW / SENT / FAILED
  private Instant createdAt = Instant.now();

  // getters/setters
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getAggregateType() { return aggregateType; }
  public void setAggregateType(String aggregateType) { this.aggregateType = aggregateType; }

  public String getAggregateId() { return aggregateId; }
  public void setAggregateId(String aggregateId) { this.aggregateId = aggregateId; }

  public String getEventType() { return eventType; }
  public void setEventType(String eventType) { this.eventType = eventType; }

  public String getPayload() { return payload; }
  public void setPayload(String payload) { this.payload = payload; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
package com.example.workorders.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "WORK_ORDERS")
public class WorkOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long workOrderId;

  private String site;
  private String assetType;
  private String assetId;
  private String priority;

  @Column(length = 2000)
  private String description;

  private String status;
  private Instant createdAt = Instant.now();

  // getters/setters
  public Long getWorkOrderId() { return workOrderId; }
  public void setWorkOrderId(Long id) { this.workOrderId = id; }

  public String getSite() { return site; }
  public void setSite(String site) { this.site = site; }

  public String getAssetType() { return assetType; }
  public void setAssetType(String assetType) { this.assetType = assetType; }

  public String getAssetId() { return assetId; }
  public void setAssetId(String assetId) { this.assetId = assetId; }

  public String getPriority() { return priority; }
  public void setPriority(String priority) { this.priority = priority; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
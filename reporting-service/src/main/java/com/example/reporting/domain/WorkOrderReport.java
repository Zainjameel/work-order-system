package com.example.reporting.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "WORK_ORDER_REPORT")
public class WorkOrderReport {
  @Id
  private Long workOrderId;

  private String site;
  private String assetType;
  private String assetId;
  private String priority;
  private String status;
  private Instant lastUpdatedAt;

  // getters/setters
  public Long getWorkOrderId() { return workOrderId; }
  public void setWorkOrderId(Long workOrderId) { this.workOrderId = workOrderId; }

  public String getSite() { return site; }
  public void setSite(String site) { this.site = site; }

  public String getAssetType() { return assetType; }
  public void setAssetType(String assetType) { this.assetType = assetType; }

  public String getAssetId() { return assetId; }
  public void setAssetId(String assetId) { this.assetId = assetId; }

  public String getPriority() { return priority; }
  public void setPriority(String priority) { this.priority = priority; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public Instant getLastUpdatedAt() { return lastUpdatedAt; }
  public void setLastUpdatedAt(Instant lastUpdatedAt) { this.lastUpdatedAt = lastUpdatedAt; }
}
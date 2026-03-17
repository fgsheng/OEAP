package com.oeap.backend.application.expert.dto;

import java.time.Instant;
import java.util.List;

public class ExpertIntakeResult {
  private String id;
  private String status;
  private List<String> gaps;
  private Instant createdAt;
  private Instant updatedAt;

  public ExpertIntakeResult(String id, String status, List<String> gaps, Instant createdAt, Instant updatedAt) {
    this.id = id;
    this.status = status;
    this.gaps = gaps;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public String getStatus() {
    return status;
  }

  public List<String> getGaps() {
    return gaps;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}

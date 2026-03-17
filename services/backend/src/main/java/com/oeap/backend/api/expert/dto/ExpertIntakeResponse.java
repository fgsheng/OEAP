package com.oeap.backend.api.expert.dto;

import java.time.Instant;
import java.util.List;

public class ExpertIntakeResponse {
  private String intakeId;
  private String status;
  private List<String> gaps;
  private Instant createdAt;
  private Instant updatedAt;

  public ExpertIntakeResponse(String intakeId, String status, List<String> gaps, Instant createdAt, Instant updatedAt) {
    this.intakeId = intakeId;
    this.status = status;
    this.gaps = gaps;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getIntakeId() {
    return intakeId;
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

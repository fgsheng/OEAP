package com.oeap.backend.expert;

import java.time.Instant;
import java.util.List;

public class ExpertIntakeRecord {
  public String id;
  public ExpertIntakeRequest request;
  public List<String> gaps;
  public String status;
  public Instant createdAt;
  public Instant updatedAt;
}

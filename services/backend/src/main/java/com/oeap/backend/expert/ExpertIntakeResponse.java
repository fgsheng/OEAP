package com.oeap.backend.expert;

import java.util.List;

public class ExpertIntakeResponse {
  public String intakeId;
  public String status;
  public List<String> gaps;

  public ExpertIntakeResponse(String intakeId, String status, List<String> gaps) {
    this.intakeId = intakeId;
    this.status = status;
    this.gaps = gaps;
  }
}

package com.oeap.backend.expert;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class ExpertIntakeRequest {
  @NotBlank
  public String goal;
  @NotBlank
  public String scope;
  @NotEmpty
  public List<String> triggers;
  @NotEmpty
  public List<String> inputs;
  @NotEmpty
  public List<String> outputs;
  @NotEmpty
  public List<String> steps;

  public List<String> decisionPoints;
  public List<String> failureRecovery;
  public List<String> tools;
  public List<String> examples;
  public List<String> assumptions;
}

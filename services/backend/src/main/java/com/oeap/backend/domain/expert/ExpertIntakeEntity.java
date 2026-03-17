package com.oeap.backend.domain.expert;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "expert_intake")
public class ExpertIntakeEntity {
  @Id
  private String id;

  private String goal;
  private String scope;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> triggers;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> inputs;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> outputs;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> steps;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> decisionPoints;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> failureRecovery;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> tools;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> examples;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> assumptions;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> gaps;

  private String status;

  private Instant createdAt;
  private Instant updatedAt;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getGoal() {
    return goal;
  }

  public void setGoal(String goal) {
    this.goal = goal;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public List<String> getTriggers() {
    return triggers;
  }

  public void setTriggers(List<String> triggers) {
    this.triggers = triggers;
  }

  public List<String> getInputs() {
    return inputs;
  }

  public void setInputs(List<String> inputs) {
    this.inputs = inputs;
  }

  public List<String> getOutputs() {
    return outputs;
  }

  public void setOutputs(List<String> outputs) {
    this.outputs = outputs;
  }

  public List<String> getSteps() {
    return steps;
  }

  public void setSteps(List<String> steps) {
    this.steps = steps;
  }

  public List<String> getDecisionPoints() {
    return decisionPoints;
  }

  public void setDecisionPoints(List<String> decisionPoints) {
    this.decisionPoints = decisionPoints;
  }

  public List<String> getFailureRecovery() {
    return failureRecovery;
  }

  public void setFailureRecovery(List<String> failureRecovery) {
    this.failureRecovery = failureRecovery;
  }

  public List<String> getTools() {
    return tools;
  }

  public void setTools(List<String> tools) {
    this.tools = tools;
  }

  public List<String> getExamples() {
    return examples;
  }

  public void setExamples(List<String> examples) {
    this.examples = examples;
  }

  public List<String> getAssumptions() {
    return assumptions;
  }

  public void setAssumptions(List<String> assumptions) {
    this.assumptions = assumptions;
  }

  public List<String> getGaps() {
    return gaps;
  }

  public void setGaps(List<String> gaps) {
    this.gaps = gaps;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }
}

package com.oeap.backend.application.expert.dto;

import java.util.List;

public class ExpertIntakeCreateCommand {
  private String goal;
  private String scope;
  private List<String> triggers;
  private List<String> inputs;
  private List<String> outputs;
  private List<String> steps;
  private List<String> decisionPoints;
  private List<String> failureRecovery;
  private List<String> tools;
  private List<String> examples;
  private List<String> assumptions;

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
}

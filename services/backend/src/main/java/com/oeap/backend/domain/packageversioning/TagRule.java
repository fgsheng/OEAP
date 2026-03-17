package com.oeap.backend.domain.packageversioning;

public class TagRule {
  private final String pattern;
  private final String example;
  private final boolean immutable;

  public TagRule(String pattern, String example, boolean immutable) {
    this.pattern = pattern;
    this.example = example;
    this.immutable = immutable;
  }

  public String getPattern() {
    return pattern;
  }

  public String getExample() {
    return example;
  }

  public boolean isImmutable() {
    return immutable;
  }
}

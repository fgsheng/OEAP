package com.oeap.backend.domain.packageversioning;

import java.util.List;

public class ChangelogRule {
  private final String format;
  private final List<String> requiredSections;
  private final String versionHeaderTemplate;

  public ChangelogRule(String format, List<String> requiredSections, String versionHeaderTemplate) {
    this.format = format;
    this.requiredSections = requiredSections;
    this.versionHeaderTemplate = versionHeaderTemplate;
  }

  public String getFormat() {
    return format;
  }

  public List<String> getRequiredSections() {
    return requiredSections;
  }

  public String getVersionHeaderTemplate() {
    return versionHeaderTemplate;
  }
}

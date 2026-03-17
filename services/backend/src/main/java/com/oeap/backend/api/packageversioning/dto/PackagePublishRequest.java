package com.oeap.backend.api.packageversioning.dto;

public class PackagePublishRequest {
  private String name;
  private String version;
  private String changelogSummary;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getChangelogSummary() {
    return changelogSummary;
  }

  public void setChangelogSummary(String changelogSummary) {
    this.changelogSummary = changelogSummary;
  }
}

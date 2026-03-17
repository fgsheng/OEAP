package com.oeap.backend.api.packageversioning.dto;

public class PackageRollbackResponse {
  private final String packageId;
  private final String name;
  private final String fromVersion;
  private final String targetVersion;
  private final String status;

  public PackageRollbackResponse(String packageId, String name, String fromVersion, String targetVersion, String status) {
    this.packageId = packageId;
    this.name = name;
    this.fromVersion = fromVersion;
    this.targetVersion = targetVersion;
    this.status = status;
  }

  public String getPackageId() {
    return packageId;
  }

  public String getName() {
    return name;
  }

  public String getFromVersion() {
    return fromVersion;
  }

  public String getTargetVersion() {
    return targetVersion;
  }

  public String getStatus() {
    return status;
  }
}

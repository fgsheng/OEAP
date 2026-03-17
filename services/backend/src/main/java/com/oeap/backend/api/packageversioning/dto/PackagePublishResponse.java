package com.oeap.backend.api.packageversioning.dto;

public class PackagePublishResponse {
  private final String packageId;
  private final String name;
  private final String version;
  private final String tag;
  private final String status;

  public PackagePublishResponse(String packageId, String name, String version, String tag, String status) {
    this.packageId = packageId;
    this.name = name;
    this.version = version;
    this.tag = tag;
    this.status = status;
  }

  public String getPackageId() {
    return packageId;
  }

  public String getName() {
    return name;
  }

  public String getVersion() {
    return version;
  }

  public String getTag() {
    return tag;
  }

  public String getStatus() {
    return status;
  }
}

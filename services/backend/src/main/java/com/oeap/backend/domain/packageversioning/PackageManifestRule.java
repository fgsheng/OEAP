package com.oeap.backend.domain.packageversioning;

import java.util.List;
import java.util.Map;

public class PackageManifestRule {
  private final String schemaVersion;
  private final List<String> requiredFields;
  private final List<String> optionalFields;
  private final Map<String, String> constraints;

  public PackageManifestRule(String schemaVersion,
                             List<String> requiredFields,
                             List<String> optionalFields,
                             Map<String, String> constraints) {
    this.schemaVersion = schemaVersion;
    this.requiredFields = requiredFields;
    this.optionalFields = optionalFields;
    this.constraints = constraints;
  }

  public String getSchemaVersion() {
    return schemaVersion;
  }

  public List<String> getRequiredFields() {
    return requiredFields;
  }

  public List<String> getOptionalFields() {
    return optionalFields;
  }

  public Map<String, String> getConstraints() {
    return constraints;
  }
}

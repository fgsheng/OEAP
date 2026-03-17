package com.oeap.backend.api.packageversioning.dto;

import com.oeap.backend.domain.packageversioning.*;

public class PackageCreateResponse {
  private final String packageId;
  private final String name;
  private final String version;
  private final String status;
  private final PackageStructureLayout structure;
  private final PackageManifestRule manifestRule;
  private final ChangelogRule changelogRule;
  private final TagRule tagRule;

  public PackageCreateResponse(String packageId,
                               String name,
                               String version,
                               String status,
                               PackageStructureLayout structure,
                               PackageManifestRule manifestRule,
                               ChangelogRule changelogRule,
                               TagRule tagRule) {
    this.packageId = packageId;
    this.name = name;
    this.version = version;
    this.status = status;
    this.structure = structure;
    this.manifestRule = manifestRule;
    this.changelogRule = changelogRule;
    this.tagRule = tagRule;
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

  public String getStatus() {
    return status;
  }

  public PackageStructureLayout getStructure() {
    return structure;
  }

  public PackageManifestRule getManifestRule() {
    return manifestRule;
  }

  public ChangelogRule getChangelogRule() {
    return changelogRule;
  }

  public TagRule getTagRule() {
    return tagRule;
  }
}

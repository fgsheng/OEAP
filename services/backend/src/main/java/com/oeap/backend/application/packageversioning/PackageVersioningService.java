package com.oeap.backend.application.packageversioning;

import com.oeap.backend.api.packageversioning.dto.*;
import com.oeap.backend.domain.packageversioning.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PackageVersioningService {
  private final PackageStructureGenerator structureGenerator = new PackageStructureGenerator();

  public PackageCreateResponse create(PackageCreateRequest request) {
    String packageId = UUID.randomUUID().toString();
    String version = request.getVersion() == null || request.getVersion().isBlank()
      ? "0.1.0"
      : request.getVersion();

    PackageStructureLayout layout = structureGenerator.defaultLayout();
    PackageManifestRule manifestRule = new PackageManifestRule(
      "v1",
      List.of("name", "version", "owner", "domain", "description"),
      List.of("dependencies", "runtime", "license", "repository", "tags"),
      Map.of(
        "version", "Semantic Versioning (MAJOR.MINOR.PATCH)",
        "name", "lowercase kebab-case",
        "owner", "org or team slug"
      )
    );
    ChangelogRule changelogRule = new ChangelogRule(
      "Keep a Changelog",
      List.of("Added", "Changed", "Deprecated", "Removed", "Fixed", "Security"),
      "## [${version}] - ${date}"
    );
    TagRule tagRule = new TagRule(
      "pkg/${name}/v${version}",
      "pkg/vision-insights/v0.1.0",
      true
    );

    return new PackageCreateResponse(
      packageId,
      request.getName(),
      version,
      "DRAFT",
      layout,
      manifestRule,
      changelogRule,
      tagRule
    );
  }

  public PackagePublishResponse publish(String packageId, PackagePublishRequest request) {
    String version = request.getVersion();
    String tag = "pkg/" + request.getName() + "/v" + version;
    return new PackagePublishResponse(packageId, request.getName(), version, tag, "PUBLISHED");
  }

  public PackageRollbackResponse rollback(String packageId, PackageRollbackRequest request) {
    return new PackageRollbackResponse(packageId, request.getName(), request.getFromVersion(), request.getTargetVersion(), "ROLLED_BACK");
  }
}

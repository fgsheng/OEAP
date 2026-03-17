package com.oeap.backend.domain.packageversioning;

import java.util.List;

public class PackageStructureGenerator {
  public PackageStructureLayout defaultLayout() {
    return new PackageStructureLayout(
      List.of(
        "src/",
        "assets/",
        "configs/",
        "docs/",
        "tests/",
        "scripts/"
      ),
      List.of(
        "manifest.yml",
        "CHANGELOG.md",
        "README.md",
        "LICENSE"
      )
    );
  }
}

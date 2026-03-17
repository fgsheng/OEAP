package com.oeap.backend.domain.packageversioning;

import java.util.List;

public class PackageStructureLayout {
  private final List<String> directories;
  private final List<String> files;

  public PackageStructureLayout(List<String> directories, List<String> files) {
    this.directories = directories;
    this.files = files;
  }

  public List<String> getDirectories() {
    return directories;
  }

  public List<String> getFiles() {
    return files;
  }
}

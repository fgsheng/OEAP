package com.oeap.backend.packagebuild;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/package-builder")
public class PackageBuildController {
  @PostMapping
  public Map<String, String> build(@RequestBody PackageBuildRequest request) {
    String packageId = UUID.randomUUID().toString();
    return Map.of("packageId", packageId, "status", "CREATED");
  }
}

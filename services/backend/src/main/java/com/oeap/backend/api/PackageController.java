package com.oeap.backend.api;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/packages")
public class PackageController {
  @PostMapping
  public Map<String, String> createPackage() {
    return Map.of("message", "TODO: create package");
  }

  @GetMapping("/{id}")
  public Map<String, String> getPackage(@PathVariable String id) {
    return Map.of("id", id, "message", "TODO: get package");
  }
}

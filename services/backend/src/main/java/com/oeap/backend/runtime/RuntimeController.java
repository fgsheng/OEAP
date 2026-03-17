package com.oeap.backend.runtime;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/runtime")
public class RuntimeController {
  @PostMapping("/load")
  public Map<String, String> load(@RequestParam String packageId) {
    return Map.of("packageId", packageId, "status", "LOADED");
  }

  @PostMapping("/execute")
  public Map<String, String> execute(@RequestParam String packageId) {
    return Map.of("packageId", packageId, "status", "EXECUTED");
  }
}

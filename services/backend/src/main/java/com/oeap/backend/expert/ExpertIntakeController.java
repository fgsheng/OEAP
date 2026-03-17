package com.oeap.backend.expert;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/expert/intake")
public class ExpertIntakeController {
  private final ExpertIntakeService service = new ExpertIntakeService();

  @PostMapping
  public ExpertIntakeResponse create(@Valid @RequestBody ExpertIntakeRequest request) {
    ExpertIntakeRecord record = service.create(request);
    return new ExpertIntakeResponse(record.id, record.status, record.gaps);
  }

  @GetMapping("/{id}")
  public Map<String, Object> get(@PathVariable String id) {
    ExpertIntakeRecord record = service.get(id);
    if (record == null) {
      return Map.of("error", "NOT_FOUND");
    }
    return Map.of(
      "id", record.id,
      "status", record.status,
      "gaps", record.gaps,
      "createdAt", record.createdAt,
      "updatedAt", record.updatedAt
    );
  }

  @PostMapping("/{id}/confirm")
  public Map<String, Object> confirm(@PathVariable String id) {
    ExpertIntakeRecord record = service.confirm(id);
    if (record == null) {
      return Map.of("error", "NOT_FOUND");
    }
    return Map.of("id", record.id, "status", record.status, "gaps", record.gaps);
  }
}

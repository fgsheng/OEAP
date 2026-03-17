package com.oeap.backend.api;

import com.oeap.backend.api.packageversioning.dto.*;
import com.oeap.backend.application.packageversioning.PackageVersioningService;
import com.oeap.backend.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/packages")
public class PackageController {
  private final PackageVersioningService service = new PackageVersioningService();

  @PostMapping
  public ApiResponse<PackageCreateResponse> createPackage(@RequestBody PackageCreateRequest request) {
    return ApiResponse.success(service.create(request));
  }

  @PostMapping("/{id}/publish")
  public ApiResponse<PackagePublishResponse> publishPackage(@PathVariable String id,
                                                            @RequestBody PackagePublishRequest request) {
    return ApiResponse.success(service.publish(id, request));
  }

  @PostMapping("/{id}/rollback")
  public ApiResponse<PackageRollbackResponse> rollbackPackage(@PathVariable String id,
                                                              @RequestBody PackageRollbackRequest request) {
    return ApiResponse.success(service.rollback(id, request));
  }

  @GetMapping("/{id}")
  public Map<String, String> getPackage(@PathVariable String id) {
    return Map.of("id", id, "message", "TODO: get package");
  }
}

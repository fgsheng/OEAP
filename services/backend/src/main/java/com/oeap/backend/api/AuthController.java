package com.oeap.backend.api;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  @PostMapping("/login")
  public Map<String, String> login() {
    return Map.of("message", "TODO: login");
  }

  @PostMapping("/refresh")
  public Map<String, String> refresh() {
    return Map.of("message", "TODO: refresh");
  }
}

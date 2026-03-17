package com.oeap.backend.api.expert;

import com.oeap.backend.api.expert.dto.ExpertIntakeCreateRequest;
import com.oeap.backend.api.expert.dto.ExpertIntakeResponse;
import com.oeap.backend.application.expert.ExpertIntakeService;
import com.oeap.backend.application.expert.dto.ExpertIntakeCreateCommand;
import com.oeap.backend.application.expert.dto.ExpertIntakeResult;
import com.oeap.backend.common.ApiResponse;
import com.oeap.backend.common.ErrorCode;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expert/intake")
public class ExpertIntakeController {
  private final ExpertIntakeService service;

  public ExpertIntakeController(ExpertIntakeService service) {
    this.service = service;
  }

  @PostMapping
  public ApiResponse<ExpertIntakeResponse> create(@Valid @RequestBody ExpertIntakeCreateRequest request) {
    ExpertIntakeCreateCommand command = toCommand(request);
    ExpertIntakeResult result = service.create(command);
    return ApiResponse.success(toResponse(result));
  }

  @GetMapping("/{id}")
  public ApiResponse<ExpertIntakeResponse> get(@PathVariable String id) {
    Optional<ExpertIntakeResult> result = service.get(id);
    if (result.isEmpty()) {
      return ApiResponse.failure(ErrorCode.NOT_FOUND, "Expert intake not found");
    }
    return ApiResponse.success(toResponse(result.get()));
  }

  @PostMapping("/{id}/confirm")
  public ApiResponse<ExpertIntakeResponse> confirm(@PathVariable String id) {
    Optional<ExpertIntakeResult> result = service.confirm(id);
    if (result.isEmpty()) {
      return ApiResponse.failure(ErrorCode.NOT_FOUND, "Expert intake not found");
    }
    return ApiResponse.success(toResponse(result.get()));
  }

  private ExpertIntakeCreateCommand toCommand(ExpertIntakeCreateRequest request) {
    ExpertIntakeCreateCommand command = new ExpertIntakeCreateCommand();
    command.setGoal(request.getGoal());
    command.setScope(request.getScope());
    command.setTriggers(request.getTriggers());
    command.setInputs(request.getInputs());
    command.setOutputs(request.getOutputs());
    command.setSteps(request.getSteps());
    command.setDecisionPoints(request.getDecisionPoints());
    command.setFailureRecovery(request.getFailureRecovery());
    command.setTools(request.getTools());
    command.setExamples(request.getExamples());
    command.setAssumptions(request.getAssumptions());
    return command;
  }

  private ExpertIntakeResponse toResponse(ExpertIntakeResult result) {
    return new ExpertIntakeResponse(
      result.getId(),
      result.getStatus(),
      result.getGaps(),
      result.getCreatedAt(),
      result.getUpdatedAt()
    );
  }
}

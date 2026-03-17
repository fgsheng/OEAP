package com.oeap.backend.application.expert;

import com.oeap.backend.application.expert.dto.ExpertIntakeCreateCommand;
import com.oeap.backend.application.expert.dto.ExpertIntakeResult;
import com.oeap.backend.domain.expert.ExpertIntakeEntity;
import com.oeap.backend.infrastructure.expert.ExpertIntakeRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpertIntakeService {
  private final ExpertIntakeRepository repository;

  public ExpertIntakeService(ExpertIntakeRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public ExpertIntakeResult create(ExpertIntakeCreateCommand command) {
    ExpertIntakeEntity entity = new ExpertIntakeEntity();
    entity.setId(UUID.randomUUID().toString());
    entity.setGoal(command.getGoal());
    entity.setScope(command.getScope());
    entity.setTriggers(command.getTriggers());
    entity.setInputs(command.getInputs());
    entity.setOutputs(command.getOutputs());
    entity.setSteps(command.getSteps());
    entity.setDecisionPoints(command.getDecisionPoints());
    entity.setFailureRecovery(command.getFailureRecovery());
    entity.setTools(command.getTools());
    entity.setExamples(command.getExamples());
    entity.setAssumptions(command.getAssumptions());

    List<String> gaps = detectGaps(command);
    entity.setGaps(gaps);
    entity.setStatus(gaps.isEmpty() ? "READY" : "NEEDS_INFO");
    Instant now = Instant.now();
    entity.setCreatedAt(now);
    entity.setUpdatedAt(now);

    ExpertIntakeEntity saved = repository.save(entity);
    return new ExpertIntakeResult(saved.getId(), saved.getStatus(), saved.getGaps(), saved.getCreatedAt(), saved.getUpdatedAt());
  }

  @Transactional(readOnly = true)
  public Optional<ExpertIntakeResult> get(String id) {
    return repository.findById(id)
      .map(entity -> new ExpertIntakeResult(entity.getId(), entity.getStatus(), entity.getGaps(), entity.getCreatedAt(), entity.getUpdatedAt()));
  }

  @Transactional
  public Optional<ExpertIntakeResult> confirm(String id) {
    Optional<ExpertIntakeEntity> optional = repository.findById(id);
    if (optional.isEmpty()) {
      return Optional.empty();
    }
    ExpertIntakeEntity entity = optional.get();
    entity.setStatus(entity.getGaps() == null || entity.getGaps().isEmpty() ? "CONFIRMED" : "NEEDS_INFO");
    entity.setUpdatedAt(Instant.now());
    ExpertIntakeEntity saved = repository.save(entity);
    return Optional.of(new ExpertIntakeResult(saved.getId(), saved.getStatus(), saved.getGaps(), saved.getCreatedAt(), saved.getUpdatedAt()));
  }

  private List<String> detectGaps(ExpertIntakeCreateCommand command) {
    List<String> gaps = new ArrayList<>();
    if (command.getGoal() == null || command.getGoal().isBlank()) gaps.add("业务目标缺失");
    if (command.getScope() == null || command.getScope().isBlank()) gaps.add("适用边界缺失");
    if (command.getTriggers() == null || command.getTriggers().isEmpty()) gaps.add("触发条件缺失");
    if (command.getInputs() == null || command.getInputs().isEmpty()) gaps.add("输入缺失");
    if (command.getOutputs() == null || command.getOutputs().isEmpty()) gaps.add("输出缺失");
    if (command.getSteps() == null || command.getSteps().isEmpty()) gaps.add("步骤流程缺失");
    if (command.getFailureRecovery() == null || command.getFailureRecovery().isEmpty()) gaps.add("失败恢复缺失");
    if (command.getExamples() == null || command.getExamples().isEmpty()) gaps.add("样例缺失");
    return gaps;
  }
}

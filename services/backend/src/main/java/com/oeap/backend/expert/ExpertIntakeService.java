package com.oeap.backend.expert;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ExpertIntakeService {
  private final Map<String, ExpertIntakeRecord> store = new ConcurrentHashMap<>();

  public ExpertIntakeRecord create(ExpertIntakeRequest req) {
    String id = UUID.randomUUID().toString();
    List<String> gaps = detectGaps(req);
    ExpertIntakeRecord record = new ExpertIntakeRecord();
    record.id = id;
    record.request = req;
    record.gaps = gaps;
    record.status = gaps.isEmpty() ? "READY" : "NEEDS_INFO";
    record.createdAt = Instant.now();
    record.updatedAt = Instant.now();
    store.put(id, record);
    return record;
  }

  public ExpertIntakeRecord get(String id) {
    return store.get(id);
  }

  public ExpertIntakeRecord confirm(String id) {
    ExpertIntakeRecord record = store.get(id);
    if (record == null) return null;
    record.status = record.gaps.isEmpty() ? "CONFIRMED" : "NEEDS_INFO";
    record.updatedAt = Instant.now();
    return record;
  }

  private List<String> detectGaps(ExpertIntakeRequest req) {
    List<String> gaps = new ArrayList<>();
    if (req.goal == null || req.goal.isBlank()) gaps.add("业务目标缺失");
    if (req.scope == null || req.scope.isBlank()) gaps.add("适用边界缺失");
    if (req.triggers == null || req.triggers.isEmpty()) gaps.add("触发条件缺失");
    if (req.inputs == null || req.inputs.isEmpty()) gaps.add("输入缺失");
    if (req.outputs == null || req.outputs.isEmpty()) gaps.add("输出缺失");
    if (req.steps == null || req.steps.isEmpty()) gaps.add("步骤流程缺失");
    if (req.failureRecovery == null || req.failureRecovery.isEmpty()) gaps.add("失败恢复缺失");
    if (req.examples == null || req.examples.isEmpty()) gaps.add("样例缺失");
    return gaps;
  }
}

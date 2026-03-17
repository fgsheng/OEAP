# WORKFLOW：质量门控与确认回路

**Version**: 0.1
**Date**: 2026-03-17
**Author**: Workflow Architect
**Status**: Draft

## Overview
定义从需求形成到方案产出全过程的质量门控、确认与回路；确保每个阶段输出可审计、可回滚、可复用。

## Actors
- 客户代表、需求负责人、方案架构师、质量官、PMO

## Workflow Tree
### GATE 1：需求完整性门控
- 覆盖率 ≥95%、关键约束明确
- 未通过 → 补采与澄清

### LOOP 1：需求确认回路
- 客户代表 + 需求负责人签字

### GATE 2：方案一致性门控
- 方案覆盖率=100%，风险成本显式
- 未通过 → 方案修订

### LOOP 2：方案评审回路
- 专家委员会评审意见闭环

### GATE 3：可落地性门控
- 资源/周期/风险可执行
- 未通过 → 方案重构

### LOOP 3：客户最终确认回路
- 客户签收版本

## Auditable Artifacts
- 需求完整性检查表
- 需求-方案可追溯矩阵
- 评审意见闭环记录
- 客户签收记录

## State Transitions
 draft → gate1_pass → confirmed → gate2_pass → reviewed → gate3_pass → signed

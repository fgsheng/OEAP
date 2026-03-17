# MCP接口规范与CLI→MCP适配方案（企业版）

> 角色：MCP Builder
> 目标：将企业现有 CLI 能力可靠、可审计、可隔离地暴露为 MCP 工具与资源接口。

## 1. 适配目标与企业约束
- 权限（RBAC/ABAC）
- 审计（全链路追溯）
- 合规（脱敏、留存）
- 隔离（多租户/沙箱）

## 2. MCP 企业接口规范
- 命名：`verb_object[_scope]`
- 参数：Zod 强类型
- 错误：`PERMISSION_DENIED|VALIDATION_ERROR|EXEC_ERROR`

## 3. 企业适配架构
AI Agent → MCP Server → Policy Gate → Audit Logger → CLI Adapter → Sandbox

## 4. CLI→Tool 映射模板
- CLI 能力 → MCP Tool + 权限策略 + 审计级别 + 隔离级别

## 5. 治理与合规
- 高风险工具需审批
- 敏感字段脱敏
- 多租户隔离验证

## 6. 部署与监控
- MCP 与 CLI 执行节点解耦
- 指标：成功率、拒绝率、执行耗时

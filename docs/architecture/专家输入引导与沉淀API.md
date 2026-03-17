# 专家输入引导与沉淀 API（草案）

## POST /api/v1/expert/intake
提交专家输入（草案）。

**请求字段（核心）**
- goal：业务目标
- scope：适用边界
- triggers：触发条件
- inputs：输入
- outputs：输出
- steps：流程步骤

**可选**
- decisionPoints / failureRecovery / tools / examples / assumptions

**响应**
- intakeId
- status：READY / NEEDS_INFO
- gaps：缺失项列表

## GET /api/v1/expert/intake/{id}
获取当前状态与缺失项。

## POST /api/v1/expert/intake/{id}/confirm
确认草案。

> 说明：后续将扩展为版本化、变更审计与本体/skill/MCP 映射。

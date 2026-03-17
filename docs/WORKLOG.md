# 工作日志

## 2026-03-17
- 初始化项目结构与 Git 仓库。
- 记录需求说明与执行计划。
- 生成 Docker Compose（Postgres/Neo4j/Qdrant/Redis）。
- 输出系统概览、多智能体流程、本地运行手册。
- 完成：MCP接口规范、LangGraph运行时、总体架构与集成模式、服务拆分与数据模型、专家输入沉淀流程。
- 创建后端/前端工程骨架、Dockerfiles、应用编排 compose。
- 增加专家输入引导 API 与前端 MVP 页面。
- 添加产品包结构/构建流程与运行时基础 API 骨架。
- 由专业角色重做：产品目标/MVP、流程门控、架构、MCP、LangGraph、UI 指导。
- 落盘：RBAC/ABAC 与 Gate/Loop 规格。
- 落盘：端到端验收报告草案与运行验收检查表。
- 落盘：API 接口验收清单。
- 落盘：能源专家访谈 CUI 对话脚本与结构化输入。
- 新增 LangGraph 运行时服务骨架（FastAPI 入口、graphs/runs/events API、engine/scheduler/state 抽象、基础配置与 README）。
- 补充 LangGraph 运行时联调方案：新增 runtime/audit 抽象、SSE/WebSocket 事件流接口、审计 API 与对接说明。
- 前端落地：新增 layout tokens、AppShell、Sidebar/Topbar、四大页面骨架与路由框架。
- 后端重构：按 api/application/domain/infrastructure/common 包级模块化重构 ExpertIntake，新增 JPA Entity/Repository、DTO 分层、ApiResponse/ErrorCode，保持原接口路径。
- 前端深化：专家输入缺失项回问逻辑与交互补充、运营控制台联调占位表格、产品包详情与运行时执行页骨架。
- 新增产品包 Git 版本化骨架：目录结构生成器、manifest/changelog/tag 规则、create/publish/rollback API 骨架与架构文档。
- 新增 CUI（Textual）原型：services/cui 目录、App 入口、专家沉淀/真实用户运行/销售工程师脚本三套引导流程，支持结构化 JSON 保存。
- 落盘：能源客户方案草案与 CUI 字段映射表（docs/verification/evidence/proposal-draft.md）。
- 落盘：方案草案与 CUI 字段对齐版本（`docs/verification/evidence/proposal-draft.md`），含字段映射表与证据索引。

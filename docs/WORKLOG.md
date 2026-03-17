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
- 新增 LangGraph 运行时服务骨架（FastAPI 入口、graphs/runs/events API、engine/scheduler/state 抽象、基础配置与 README）。
- 前端落地：新增 layout tokens、AppShell、Sidebar/Topbar、四大页面骨架与路由框架。
- 后端重构：按 api/application/domain/infrastructure/common 包级模块化重构 ExpertIntake，新增 JPA Entity/Repository、DTO 分层、ApiResponse/ErrorCode，保持原接口路径。

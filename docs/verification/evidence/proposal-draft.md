# 企业级 AI 平台方案草案（CUI 字段对齐版）

> 角色：Proposal Strategist  
> 目标：将方案叙事与 CUI 关键字段一一对齐，确保“所见即所答”，并提供可核验证据。  
> 证据基线：`docs/verification/evidence/*` 与现有架构/需求文档。

---

## 一、执行摘要（Executive Summary）
贵方需要把“专家经验”从碎片化文本转化为可审计、可复用、可运营的企业级智能资产，同时保证**合规、可追溯、可扩展**。当前痛点在于：专家输入缺乏结构化引导、产品包版本与依赖难追踪、运行时执行缺乏统一观测，导致“落地慢、复用难、风险高”。

本方案以**专家输入→知识/技能/工具沉淀→产品包→运行时→前端验证**为主链路，构建端到端可验证闭环：
- **结构化采集**：专家输入页以字段化引导+缺失回问，确保输入完整性与可追溯性。
- **资产化沉淀**：技能/工具/MCP 接口明确版本与依赖，保证复用与审计。
- **运行时可信执行**：加载、执行、健康与负载可视化，保证可观测与合规。

**可验证证据**已落盘（`expert-intake.json`, `package-build.json`, `runtime-load.json`, `runtime-execute.json`, `runtime-health.json`），证明链路闭环与系统可执行性。

---

## 二、核心赢标主题（Win Themes）

### 主题 1：从“专家经验”到“可审计智能资产”的全链路闭环
- **买方需求**：输入内容可追溯、可审计、可复用
- **差异化能力**：专家输入→产品包→运行时的闭环链路与证据机制
- **证明点**：`docs/verification/evidence/expert-intake.json`、`runtime-*.json`

### 主题 2：字段驱动的质量门控，避免“输入垃圾、输出风险”
- **买方需求**：确保输入完整、合规、可校验
- **差异化能力**：CUI 字段与质控规则绑定，缺失/错误即时反馈
- **证明点**：验收清单（`docs/verification/API接口验收清单.md`）

### 主题 3：企业级合规与治理内嵌在架构，而非事后补丁
- **买方需求**：权限/审计/脱敏/隔离
- **差异化能力**：MCP 企业级适配与治理约束原生设计
- **证明点**：`docs/architecture/MCP接口规范与CLI→MCP适配方案（企业版）.md`

---

## 三、三幕式方案叙事（Three-Act Narrative）

### Act I — 理解挑战（问题态）
- 专家输入存在非结构化、缺字段、难复用问题
- 产品包版本、依赖关系不透明
- 运行时缺少统一观测与审计闭环

### Act II — 解决旅程（方案态）
- **专家输入引导**：结构化字段 + 质量校验
- **知识/技能/工具资产化**：版本化与依赖管理
- **运行时加载/执行/观测**：可追溯、可审计

### Act III — 未来状态（结果态）
- 输入可控、资产可复用、执行可审计
- 组织形成“知识→产品→运行”的稳定生产线
- 企业级治理内嵌，成本可控、风险可见

---

## 四、CUI 字段对齐表（Proposal ↔ CUI）

> 来源：`docs/ui/enterprise-tech-ui-guidelines-and-ia.md`

| CUI 页面/字段 | 字段定义（业务语义） | 方案草案对应内容 | 验证证据/落盘位置 |
|---|---|---|---|
| 专家输入页 / 输入区 | 专家业务描述与目标诉求 | “结构化采集 + 引导式补齐” | `docs/verification/evidence/expert-intake.json` |
| 专家输入页 / 参数区 | 关键变量、边界条件 | “字段驱动质控，缺失/错误反馈” | `docs/verification/API接口验收清单.md`（EI-01~EI-07） |
| 专家输入页 / 附件 | 佐证材料与上下文 | “输入与证据绑定，保证可追溯” | 证据机制说明：`docs/verification/端到端验收报告（草案）.md` |
| 专家输入页 / 合规提示 | 合规声明/脱敏/权限 | “合规内嵌设计（RBAC/审计/脱敏）” | `docs/architecture/MCP接口规范与CLI→MCP适配方案（企业版）.md` |
| 专家输入页 / 预览与校验 | 提交前完整性检查 | “质量门控 + 校验反馈闭环” | `docs/verification/API接口验收清单.md` |
| 运营控制台 / KPI 总览 | 核心运营指标 | “运营视角可观测与闭环治理” | `docs/REQUIREMENTS.md`（运营控制台） |
| 运营控制台 / 任务监控 | 任务状态与异常 | “运行时执行/加载可视化” | `docs/verification/evidence/runtime-load.json` / `runtime-execute.json` |
| 运营控制台 / 运行警报 | 异常告警与追踪 | “可观测与审计链路” | `docs/verification/API接口验收清单.md`（审计相关） |
| 运营控制台 / 资源负载 | 运行负载指标 | “运行时健康与负载可观测” | `docs/verification/evidence/runtime-health.json` |
| 产品包详情页 / 版本 | 版本号与变更记录 | “版本化产品包 + 依赖治理” | `docs/architecture/PACKAGE_VERSIONING.md` |
| 产品包详情页 / 依赖 | 依赖包与校验 | “依赖与完整性校验” | `docs/verification/API接口验收清单.md`（PP-03~PP-06） |
| 产品包详情页 / 权限 | 访问与审批策略 | “RBAC/ABAC 约束” | `docs/architecture/MCP接口规范与CLI→MCP适配方案（企业版）.md` |
| 产品包详情页 / 安全扫描 | 风险检测 | “合规与脱敏策略” | `docs/architecture/RBAC_ABAC_AUDIT_PLAN.md` |
| 产品包详情页 / 历史版本 | 历史追溯 | “版本化+审计链路” | `docs/architecture/PACKAGE_VERSIONING.md` |
| 运行时执行页 / 状态 | 执行状态可视化 | “加载/执行闭环” | `docs/verification/evidence/runtime-load.json` / `runtime-execute.json` |
| 运行时执行页 / 进度 | 任务进度 | “运行时执行可观测” | `docs/verification/evidence/runtime-execute.json` |
| 运行时执行页 / 日志 | 执行日志 | “审计与追溯” | `docs/verification/API接口验收清单.md`（审计） |
| 运行时执行页 / 性能指标 | 关键性能指标 | “运行时健康与负载” | `docs/verification/evidence/runtime-health.json` |
| 运行时执行页 / 操作区 | 启停/干预 | “治理与审批策略” | `docs/architecture/MCP接口规范与CLI→MCP适配方案（企业版）.md` |

---

## 五、关键证据索引（Evidence Index）

- **专家输入回执**：`docs/verification/evidence/expert-intake.json`
- **产品包构建**：`docs/verification/evidence/package-build.json`
- **运行时加载**：`docs/verification/evidence/runtime-load.json`
- **运行时执行**：`docs/verification/evidence/runtime-execute.json`
- **运行时健康**：`docs/verification/evidence/runtime-health.json`

---

## 六、建议的下一步
1. 依据 CUI 字段完成前端字段级联与验证规则绑定（EI-01~EI-07）。
2. 补充运行时指标采集与告警策略定义，形成运营闭环。
3. 将本方案草案与验收清单对齐，形成可交付的验收计划与里程碑。

---

> 说明：如需更精细字段（CUI 组件级别）映射，请补充 UI 细化稿或提供前端字段清单（当前仅基于 IA 与验收文档对齐）。

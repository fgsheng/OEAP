# 企业级平台服务拆分与数据模型（能源需求挖掘→方案→产品包→运行时）

**角色**：Backend Architect
**建议文件名**：enterprise_energy_platform_architecture_v1.md

## 1. 服务边界
- Tenant & IAM
- Energy Discovery（需求挖掘）
- Solution Design（方案设计）
- Product Package（产品包）
- Runtime Orchestration（运行时）
- Contract & Billing
- Integration
- Knowledge Graph
- Vector Retrieval
- Audit & Compliance
- Event Bus & Workflow

## 2. REST API（示例）
- /v1/discovery/jobs
- /v1/solutions
- /v1/packages
- /v1/runtime/projects
- /v1/audit/events

## 3. Postgres 模型（摘要）
- tenants, users, sites
- discovery_jobs, insights
- solutions, solution_items
- packages, runtime_projects
- contracts, billing_records
- audit_logs

## 4. Neo4j 模型
- Tenant/Site/Asset/EnergySource/Solution/Package/RuntimeProject

## 5. Qdrant
- solution_vectors / knowledge_vectors

## 6. Redis
- tenant config / kpi snapshot / idempotent keys / queues

## 7. 审计链路
- Postgres + WORM + 哈希链

## 8. 事件总线
- discovery.job.created/completed
- solution.created/validated
- package.published
- runtime.project.deployed/failed

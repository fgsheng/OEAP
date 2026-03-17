# LangGraph Runtime Service (Skeleton)

> 角色：AI Engineer

该服务是 LangGraph 运行时的骨架实现，提供 FastAPI 入口与 **graphs / runs / events** API 框架，并抽象 runtime engine/scheduler/state，为后续接入企业级调度、存储与可观测能力预留扩展点。

## 目录结构

```
services/runtime/
├─ app/
│  ├─ api/                # API 路由（graphs/runs/events）
│  ├─ core/               # 配置与依赖注入
│  ├─ runtime/            # 运行时抽象（engine/scheduler/state）
│  └─ main.py             # FastAPI 入口
├─ requirements.txt
├─ .env.example
└─ README.md
```

## 快速开始

```bash
cd services/runtime
python -m venv .venv && source .venv/bin/activate
pip install -r requirements.txt

cp .env.example .env
uvicorn app.main:app --host 0.0.0.0 --port 7800 --reload
```

## API 约定（初版）

### Graphs
- `GET /api/v1/graphs`：列出已注册的 graphs
- `POST /api/v1/graphs`：注册 graph（支持 `package_id/tenant_id/actor_id`）
- `GET /api/v1/graphs/{graph_id}`：获取 graph 详情

### Runs
- `POST /api/v1/runs`：创建 run（支持 `tenant_id/actor_id/trace_id/metadata`）
- `GET /api/v1/runs/{run_id}`：获取 run 状态
- `POST /api/v1/runs/{run_id}/cancel`：取消 run

### Events
- `GET /api/v1/events`：查询事件（支持 run_id 过滤）
- `GET /api/v1/events/stream`：SSE 事件流
- `WS /api/v1/events/ws`：WebSocket 事件流

### Audit
- `GET /api/v1/audit/events`：查询审计事件（支持 entity_id 过滤）
- `POST /api/v1/audit/events`：写入审计事件

## 运行时联调对接点

**后端 → Runtime**
- `POST /api/v1/graphs`：产品包发布后注册 graph（携带 package_id）
- `POST /api/v1/runs`：创建执行实例（tenant/actor/trace 用于审计与追踪）
- `POST /api/v1/runs/{run_id}/cancel`：停止任务

**Runtime → 后端**
- 事件流：后端订阅 `GET /api/v1/events/stream` 或 `WS /api/v1/events/ws`，同步运行状态
- 审计写入：runtime 在 graph/run 生命周期中写入审计事件（示例字段：actor/tenant/action/trace）

## 事件流与审计写入方式

- **事件流**：SSE / WebSocket 两种方式，当前为内存事件缓冲与轮询推送（后续可替换 Kafka/Redis Stream）。
- **审计写入**：在 `graph.registered`、`run.created`、`run.cancelled` 等关键动作上生成审计事件，可对接后端审计服务或事件总线。

## 后续扩展

- 接入 LangGraph 实际执行引擎（Node/Edge runtime）
- Scheduler 与 StateStore 替换为分布式实现（Redis/Postgres/Neo4j）
- 事件流（SSE/Kafka）与可观测（OpenTelemetry）
- 多租户/鉴权/审计

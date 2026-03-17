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

- `GET /api/v1/graphs`：列出已注册的 graphs
- `POST /api/v1/graphs`：注册 graph
- `GET /api/v1/graphs/{graph_id}`：获取 graph 详情

- `POST /api/v1/runs`：创建 run
- `GET /api/v1/runs/{run_id}`：获取 run 状态
- `POST /api/v1/runs/{run_id}/cancel`：取消 run

- `GET /api/v1/events`：查询事件（支持 run_id 过滤）

## 后续扩展

- 接入 LangGraph 实际执行引擎（Node/Edge runtime）
- Scheduler 与 StateStore 替换为分布式实现（Redis/Postgres/Neo4j）
- 事件流（SSE/Kafka）与可观测（OpenTelemetry）
- 多租户/鉴权/审计

# 本地开发（初稿）

## 启动依赖
```bash
cd infra
docker compose up -d
```

## 健康检查
- Postgres: localhost:5432
- Neo4j: http://localhost:7474
- Qdrant: http://localhost:6333/healthz
- Redis: localhost:6379

## 说明
后端与前端服务骨架已就绪。

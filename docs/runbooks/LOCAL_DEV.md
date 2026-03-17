# Local Development (Draft)

## Start Dependencies
```bash
cd infra
docker compose up -d
```

## Health Checks
- Postgres: localhost:5432
- Neo4j: http://localhost:7474
- Qdrant: http://localhost:6333/healthz
- Redis: localhost:6379

## Notes
Backend and frontend services will be added next.

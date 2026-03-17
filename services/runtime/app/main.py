from __future__ import annotations

from fastapi import FastAPI

from .api import events, graphs, runs
from .core.config import settings

app = FastAPI(
    title="LangGraph Runtime Service",
    version="0.1.0",
    description="LangGraph runtime skeleton for enterprise AI platform",
)

app.include_router(graphs.router, prefix="/api/v1")
app.include_router(runs.router, prefix="/api/v1")
app.include_router(events.router, prefix="/api/v1")


@app.get("/health")
def health() -> dict:
    return {
        "status": "ok",
        "service": settings.runtime_service_name,
        "env": settings.runtime_env,
    }

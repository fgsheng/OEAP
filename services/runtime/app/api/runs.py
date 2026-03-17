from __future__ import annotations

import uuid
from datetime import datetime

from fastapi import APIRouter, HTTPException

from .audit import audit_store
from .schemas import RunCreate, RunResponse
from ..runtime.audit import AuditEvent
from ..runtime.engine import InProcessEngine
from ..runtime.models import RunSpec
from ..runtime.scheduler import ImmediateScheduler
from ..runtime.state import InMemoryStateStore

router = APIRouter(prefix="/runs", tags=["runs"])

state_store = InMemoryStateStore()
engine = InProcessEngine(state_store)
scheduler = ImmediateScheduler(engine, state_store)


@router.post("", response_model=RunResponse)
def create_run(payload: RunCreate) -> RunResponse:
    run = RunSpec(
        run_id=f"run-{uuid.uuid4().hex[:8]}",
        graph_id=payload.graph_id,
        input=payload.input,
        status="queued",
        tenant_id=payload.tenant_id,
        actor_id=payload.actor_id,
        trace_id=payload.trace_id,
        metadata=payload.metadata,
        created_at=datetime.utcnow(),
        updated_at=datetime.utcnow(),
    )
    state_store.put_run(run)
    scheduler.enqueue(run)
    audit_store.append(
        AuditEvent(
            audit_id=f"audit-{uuid.uuid4().hex[:10]}",
            actor_id=payload.actor_id,
            tenant_id=payload.tenant_id,
            action="runtime.run.created",
            entity_type="run",
            entity_id=run.run_id,
            after=run.__dict__,
            trace_id=payload.trace_id,
        )
    )
    return RunResponse(**run.__dict__)


@router.get("/{run_id}", response_model=RunResponse)
def get_run(run_id: str) -> RunResponse:
    run = state_store.get_run(run_id)
    if not run:
        raise HTTPException(status_code=404, detail="run_not_found")
    return RunResponse(**run.__dict__)


@router.post("/{run_id}/cancel", response_model=RunResponse)
def cancel_run(run_id: str) -> RunResponse:
    run = engine.cancel(run_id)
    if not run:
        raise HTTPException(status_code=404, detail="run_not_found")
    audit_store.append(
        AuditEvent(
            audit_id=f"audit-{uuid.uuid4().hex[:10]}",
            actor_id=run.actor_id,
            tenant_id=run.tenant_id,
            action="runtime.run.cancelled",
            entity_type="run",
            entity_id=run.run_id,
            after=run.__dict__,
            trace_id=run.trace_id,
        )
    )
    return RunResponse(**run.__dict__)

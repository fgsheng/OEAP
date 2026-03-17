from __future__ import annotations

import uuid
from datetime import datetime

from fastapi import APIRouter, HTTPException

from .schemas import RunCreate, RunResponse
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
        created_at=datetime.utcnow(),
        updated_at=datetime.utcnow(),
    )
    state_store.put_run(run)
    scheduler.enqueue(run)
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
    return RunResponse(**run.__dict__)

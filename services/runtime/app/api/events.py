from __future__ import annotations

from fastapi import APIRouter, Query

from .schemas import RunEventResponse
from .runs import state_store

router = APIRouter(prefix="/events", tags=["events"])


@router.get("", response_model=list[RunEventResponse])
def list_events(run_id: str | None = Query(default=None)) -> list[RunEventResponse]:
    events = state_store.list_events(run_id)
    return [RunEventResponse(**event.__dict__) for event in events]

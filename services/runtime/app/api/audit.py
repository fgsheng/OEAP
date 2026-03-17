from __future__ import annotations

import uuid

from fastapi import APIRouter, Query

from .schemas import AuditEventCreate, AuditEventResponse
from ..runtime.audit import AuditEvent, InMemoryAuditStore

router = APIRouter(prefix="/audit", tags=["audit"])

audit_store = InMemoryAuditStore()


@router.get("/events", response_model=list[AuditEventResponse])
def list_audit_events(entity_id: str | None = Query(default=None)) -> list[AuditEventResponse]:
    events = audit_store.list(entity_id)
    return [AuditEventResponse(**event.__dict__) for event in events]


@router.post("/events", response_model=AuditEventResponse)
def create_audit_event(payload: AuditEventCreate) -> AuditEventResponse:
    event = AuditEvent(
        audit_id=f"audit-{uuid.uuid4().hex[:10]}",
        actor_id=payload.actor_id,
        tenant_id=payload.tenant_id,
        action=payload.action,
        entity_type=payload.entity_type,
        entity_id=payload.entity_id,
        before=payload.before,
        after=payload.after,
        trace_id=payload.trace_id,
    )
    audit_store.append(event)
    return AuditEventResponse(**event.__dict__)

from __future__ import annotations

from datetime import datetime
from typing import Any, Dict, Optional

from pydantic import BaseModel, Field


class GraphCreate(BaseModel):
    graph_id: str = Field(..., examples=["graph-energy-audit"])
    name: str
    description: Optional[str] = None
    version: str = "v1"
    package_id: Optional[str] = None
    tenant_id: Optional[str] = None
    actor_id: Optional[str] = None
    metadata: Dict[str, Any] = Field(default_factory=dict)


class GraphResponse(GraphCreate):
    created_at: datetime


class RunCreate(BaseModel):
    graph_id: str
    input: Dict[str, Any] = Field(default_factory=dict)
    tenant_id: Optional[str] = None
    actor_id: Optional[str] = None
    trace_id: Optional[str] = None
    metadata: Dict[str, Any] = Field(default_factory=dict)


class RunResponse(BaseModel):
    run_id: str
    graph_id: str
    status: str
    input: Dict[str, Any]
    tenant_id: Optional[str] = None
    actor_id: Optional[str] = None
    trace_id: Optional[str] = None
    metadata: Dict[str, Any] = Field(default_factory=dict)
    created_at: datetime
    updated_at: datetime
    error: Optional[str] = None


class RunEventResponse(BaseModel):
    event_id: str
    run_id: str
    type: str
    payload: Dict[str, Any]
    created_at: datetime


class AuditEventCreate(BaseModel):
    actor_id: Optional[str] = None
    tenant_id: Optional[str] = None
    action: str
    entity_type: str
    entity_id: str
    before: Dict[str, Any] = Field(default_factory=dict)
    after: Dict[str, Any] = Field(default_factory=dict)
    trace_id: Optional[str] = None


class AuditEventResponse(AuditEventCreate):
    audit_id: str
    created_at: datetime

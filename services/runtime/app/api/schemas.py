from __future__ import annotations

from datetime import datetime
from typing import Any, Dict, Optional

from pydantic import BaseModel, Field


class GraphCreate(BaseModel):
    graph_id: str = Field(..., examples=["graph-energy-audit"])
    name: str
    description: Optional[str] = None
    version: str = "v1"
    metadata: Dict[str, Any] = Field(default_factory=dict)


class GraphResponse(GraphCreate):
    created_at: datetime


class RunCreate(BaseModel):
    graph_id: str
    input: Dict[str, Any] = Field(default_factory=dict)


class RunResponse(BaseModel):
    run_id: str
    graph_id: str
    status: str
    input: Dict[str, Any]
    created_at: datetime
    updated_at: datetime
    error: Optional[str] = None


class RunEventResponse(BaseModel):
    event_id: str
    run_id: str
    type: str
    payload: Dict[str, Any]
    created_at: datetime

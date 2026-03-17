from __future__ import annotations

from abc import ABC, abstractmethod
from dataclasses import dataclass, field
from datetime import datetime
from typing import Any, Dict, Iterable, Optional


@dataclass
class AuditEvent:
    audit_id: str
    actor_id: Optional[str]
    tenant_id: Optional[str]
    action: str
    entity_type: str
    entity_id: str
    before: Dict[str, Any] = field(default_factory=dict)
    after: Dict[str, Any] = field(default_factory=dict)
    trace_id: Optional[str] = None
    created_at: datetime = field(default_factory=datetime.utcnow)


class AuditStore(ABC):
    @abstractmethod
    def append(self, event: AuditEvent) -> AuditEvent:
        raise NotImplementedError

    @abstractmethod
    def list(self, entity_id: Optional[str] = None) -> Iterable[AuditEvent]:
        raise NotImplementedError


class InMemoryAuditStore(AuditStore):
    def __init__(self) -> None:
        self._events: Dict[str, AuditEvent] = {}

    def append(self, event: AuditEvent) -> AuditEvent:
        self._events[event.audit_id] = event
        return event

    def list(self, entity_id: Optional[str] = None) -> Iterable[AuditEvent]:
        events = list(self._events.values())
        if entity_id:
            events = [event for event in events if event.entity_id == entity_id]
        return sorted(events, key=lambda item: item.created_at)

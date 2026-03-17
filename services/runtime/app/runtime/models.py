from __future__ import annotations

from dataclasses import dataclass, field
from datetime import datetime
from typing import Any, Dict, List, Optional


@dataclass
class GraphSpec:
    graph_id: str
    name: str
    description: Optional[str] = None
    version: str = "v1"
    package_id: Optional[str] = None
    tenant_id: Optional[str] = None
    actor_id: Optional[str] = None
    metadata: Dict[str, Any] = field(default_factory=dict)
    created_at: datetime = field(default_factory=datetime.utcnow)


@dataclass
class RunSpec:
    run_id: str
    graph_id: str
    input: Dict[str, Any]
    status: str = "queued"
    tenant_id: Optional[str] = None
    actor_id: Optional[str] = None
    trace_id: Optional[str] = None
    metadata: Dict[str, Any] = field(default_factory=dict)
    created_at: datetime = field(default_factory=datetime.utcnow)
    updated_at: datetime = field(default_factory=datetime.utcnow)
    error: Optional[str] = None


@dataclass
class RunEvent:
    event_id: str
    run_id: str
    type: str
    payload: Dict[str, Any]
    created_at: datetime = field(default_factory=datetime.utcnow)


@dataclass
class GraphRegistry:
    graphs: Dict[str, GraphSpec] = field(default_factory=dict)

    def list(self) -> List[GraphSpec]:
        return list(self.graphs.values())

    def get(self, graph_id: str) -> Optional[GraphSpec]:
        return self.graphs.get(graph_id)

    def register(self, graph: GraphSpec) -> GraphSpec:
        self.graphs[graph.graph_id] = graph
        return graph

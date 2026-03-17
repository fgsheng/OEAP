from __future__ import annotations

from fastapi import APIRouter, HTTPException

from .schemas import GraphCreate, GraphResponse
from ..runtime.models import GraphSpec, GraphRegistry

router = APIRouter(prefix="/graphs", tags=["graphs"])


class GraphsService:
    def __init__(self) -> None:
        self._registry = GraphRegistry()

    @property
    def registry(self) -> GraphRegistry:
        return self._registry


graphs_service = GraphsService()


@router.get("", response_model=list[GraphResponse])
def list_graphs() -> list[GraphResponse]:
    return [GraphResponse(**graph.__dict__) for graph in graphs_service.registry.list()]


@router.post("", response_model=GraphResponse)
def create_graph(payload: GraphCreate) -> GraphResponse:
    graph = GraphSpec(
        graph_id=payload.graph_id,
        name=payload.name,
        description=payload.description,
        version=payload.version,
        metadata=payload.metadata,
    )
    graphs_service.registry.register(graph)
    return GraphResponse(**graph.__dict__)


@router.get("/{graph_id}", response_model=GraphResponse)
def get_graph(graph_id: str) -> GraphResponse:
    graph = graphs_service.registry.get(graph_id)
    if not graph:
        raise HTTPException(status_code=404, detail="graph_not_found")
    return GraphResponse(**graph.__dict__)

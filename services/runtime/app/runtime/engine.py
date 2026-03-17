from __future__ import annotations

from abc import ABC, abstractmethod
from datetime import datetime
from typing import Dict, Optional

from .models import RunEvent, RunSpec
from .state import StateStore


class RuntimeEngine(ABC):
    @abstractmethod
    def submit(self, run: RunSpec) -> RunSpec:
        raise NotImplementedError

    @abstractmethod
    def get(self, run_id: str) -> Optional[RunSpec]:
        raise NotImplementedError

    @abstractmethod
    def cancel(self, run_id: str) -> Optional[RunSpec]:
        raise NotImplementedError


class InProcessEngine(RuntimeEngine):
    """默认的本地引擎，后续可替换为 LangGraph Executor。"""

    def __init__(self, state: StateStore) -> None:
        self._state = state

    def submit(self, run: RunSpec) -> RunSpec:
        run.status = "running"
        run.updated_at = datetime.utcnow()
        self._state.update_run(run)
        self._state.append_event(
            RunEvent(
                event_id=f"evt-{run.run_id}-start",
                run_id=run.run_id,
                type="run.started",
                payload={"graph_id": run.graph_id},
            )
        )
        # TODO: 接入 LangGraph runtime 执行
        return run

    def get(self, run_id: str) -> Optional[RunSpec]:
        return self._state.get_run(run_id)

    def cancel(self, run_id: str) -> Optional[RunSpec]:
        run = self._state.get_run(run_id)
        if not run:
            return None
        run.status = "cancelled"
        run.updated_at = datetime.utcnow()
        self._state.update_run(run)
        self._state.append_event(
            RunEvent(
                event_id=f"evt-{run.run_id}-cancel",
                run_id=run.run_id,
                type="run.cancelled",
                payload={},
            )
        )
        return run

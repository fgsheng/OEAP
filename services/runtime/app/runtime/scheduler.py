from __future__ import annotations

from abc import ABC, abstractmethod
from datetime import datetime

from .engine import RuntimeEngine
from .models import RunSpec
from .state import StateStore


class Scheduler(ABC):
    @abstractmethod
    def enqueue(self, run: RunSpec) -> RunSpec:
        raise NotImplementedError


class ImmediateScheduler(Scheduler):
    """默认调度器：创建后立即交给 engine 执行。"""

    def __init__(self, engine: RuntimeEngine, state: StateStore) -> None:
        self._engine = engine
        self._state = state

    def enqueue(self, run: RunSpec) -> RunSpec:
        run.status = "queued"
        run.updated_at = datetime.utcnow()
        self._state.update_run(run)
        return self._engine.submit(run)

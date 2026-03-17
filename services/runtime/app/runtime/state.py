from __future__ import annotations

from abc import ABC, abstractmethod
from typing import Dict, Iterable, Optional

from .models import RunEvent, RunSpec


class StateStore(ABC):
    @abstractmethod
    def put_run(self, run: RunSpec) -> RunSpec:
        raise NotImplementedError

    @abstractmethod
    def get_run(self, run_id: str) -> Optional[RunSpec]:
        raise NotImplementedError

    @abstractmethod
    def update_run(self, run: RunSpec) -> RunSpec:
        raise NotImplementedError

    @abstractmethod
    def list_runs(self) -> Iterable[RunSpec]:
        raise NotImplementedError

    @abstractmethod
    def append_event(self, event: RunEvent) -> RunEvent:
        raise NotImplementedError

    @abstractmethod
    def list_events(self, run_id: Optional[str] = None) -> Iterable[RunEvent]:
        raise NotImplementedError


class InMemoryStateStore(StateStore):
    def __init__(self) -> None:
        self._runs: Dict[str, RunSpec] = {}
        self._events: Dict[str, RunEvent] = {}

    def put_run(self, run: RunSpec) -> RunSpec:
        self._runs[run.run_id] = run
        return run

    def get_run(self, run_id: str) -> Optional[RunSpec]:
        return self._runs.get(run_id)

    def update_run(self, run: RunSpec) -> RunSpec:
        self._runs[run.run_id] = run
        return run

    def list_runs(self) -> Iterable[RunSpec]:
        return list(self._runs.values())

    def append_event(self, event: RunEvent) -> RunEvent:
        self._events[event.event_id] = event
        return event

    def list_events(self, run_id: Optional[str] = None) -> Iterable[RunEvent]:
        events = list(self._events.values())
        if run_id:
            events = [event for event in events if event.run_id == run_id]
        return sorted(events, key=lambda item: item.created_at)

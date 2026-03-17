from __future__ import annotations

import asyncio
import json

from fastapi import APIRouter, Query, WebSocket, WebSocketDisconnect
from fastapi.responses import StreamingResponse

from .schemas import RunEventResponse
from .runs import state_store

router = APIRouter(prefix="/events", tags=["events"])


@router.get("", response_model=list[RunEventResponse])
def list_events(run_id: str | None = Query(default=None)) -> list[RunEventResponse]:
    events = state_store.list_events(run_id)
    return [RunEventResponse(**event.__dict__) for event in events]


@router.get("/stream")
async def stream_events(run_id: str | None = Query(default=None)) -> StreamingResponse:
    async def event_generator():
        seen: set[str] = set()
        while True:
            events = state_store.list_events(run_id)
            for event in events:
                if event.event_id in seen:
                    continue
                seen.add(event.event_id)
                payload = json.dumps(event.__dict__, default=str)
                yield f"data: {payload}\n\n"
            yield ": keep-alive\n\n"
            await asyncio.sleep(1)

    return StreamingResponse(event_generator(), media_type="text/event-stream")


@router.websocket("/ws")
async def ws_events(websocket: WebSocket, run_id: str | None = None) -> None:
    await websocket.accept()
    seen: set[str] = set()
    try:
        while True:
            events = state_store.list_events(run_id)
            for event in events:
                if event.event_id in seen:
                    continue
                seen.add(event.event_id)
                await websocket.send_text(json.dumps(event.__dict__, default=str))
            await asyncio.sleep(1)
    except WebSocketDisconnect:
        return

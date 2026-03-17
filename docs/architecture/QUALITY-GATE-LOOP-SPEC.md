# QUALITY-GATE-LOOP-SPEC（Gate/Loop 状态机）

## Overview
质量门控(Gate)与确认回路(Loop)落地为可执行状态机与字段模型。

## Gate/Loop 状态机
- GateRun：pending → running → passed/failed/loop_open → passed_with_loop/failed_with_loop
- LoopRun：open → in_review → closed_pass/closed_fail

## 关键 API（建议）
- `POST /gate-runs`
- `GET /gate-runs/{id}`
- `POST /gate-runs/{id}/evidence`
- `POST /loop-runs`
- `POST /loop-runs/{id}/review`

## 数据结构（摘要）
- Gate / GateRun / Loop / LoopRun / Evidence

## 关键问题
- 多 GateRun 聚合决策
- 是否支持强制豁免

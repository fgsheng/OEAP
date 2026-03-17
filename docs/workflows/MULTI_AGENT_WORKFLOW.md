# Multi-Agent Workflow (Draft)

## Orchestrator Pattern
- **Main Orchestrator** (you + OpenClaw) decomposes tasks and dispatches agents.
- **Parallel first** when outputs are independent.
- **Merge point** before implementation.
- **Reality Checker** gates quality at each milestone.

## Default Workflow for OEAP
1) Product Manager — vision, personas, scope
2) Workflow Architect — expert input -> structured knowledge process
3) Backend Architect — service decomposition + data model
4) Software Architect — architecture patterns, integration choices
5) MCP Builder — MCP tool schema + CLI wrappers
6) AI Engineer — LangGraph runtime plan
7) Security Engineer + SRE — security & reliability baseline
8) Reality Checker — verify completeness and risks

## Output Rules
- Each agent must write outputs to docs/ with timestamps.
- Orchestrator merges into a single deliverable summary.
- All outputs logged in docs/WORKLOG.md.

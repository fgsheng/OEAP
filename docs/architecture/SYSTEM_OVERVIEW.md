# System Overview (Draft)

## Core Concepts
- **Expert Guided Capture**: guided natural-language input to capture domain practice.
- **Ontology-backed Skills**: structured skills derived from validated expert input.
- **MCP Tooling**: operations extracted from expert workflows and mapped to MCP interfaces, including CLI-to-MCP wrappers for legacy systems.
- **Product Package**: versioned bundle of ontology + skills + MCP tools in Git.
- **Runtime**: multi-agent execution platform that loads packages and orchestrates agents with ontology context.

## High-Level Architecture
1. **Expert Intake Service** — guided capture, validation, and confirmation loops.
2. **Knowledge & Ontology Service** — stores domain entities, relations, and rules.
3. **Skill Builder Service** — transforms validated inputs into skills and tests.
4. **MCP Registry & Adapter Service** — maintains MCP interfaces and CLI wrappers.
5. **Package Service** — builds, versions, and stores product packages in Git.
6. **Runtime Orchestrator** — loads packages and runs multi-agent workflows (LangGraph).
7. **Ops Console** — expert management, package inventory, usage analytics.
8. **Identity & Access** — standalone auth now, federated later (DingTalk).

## Data Stores
- Postgres: primary relational data
- Neo4j: ontology graph
- Qdrant: vector search
- Redis: cache/session

## Next
- Confirm service boundaries and API contracts
- Define package schema and Git layout
- Define MCP adapter lifecycle

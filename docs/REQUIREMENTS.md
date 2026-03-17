# Requirements (Draft)

## Roles
- Expert: provides domain practice in natural language; system guides missing info.
- Operator: manages experts, packages, analytics, governance.
- End User: consumes packaged intelligent products.

## Core Capabilities
1) Expert Input & Guidance
- Guided, stepwise capture of domain practice.
- Detect missing/ambiguous info, prompt for confirmation.
- Validate for completeness and quality.

2) Knowledge -> Skills -> MCP Tools
- Convert expert narratives into ontology-backed skills.
- Extract operations into MCP interfaces; map to existing system CLI -> MCP.

3) Product Package
- Bundle skills + MCP tools + ontology into a versioned product package.
- Store packages in Git with metadata, changelog, and provenance.

4) Runtime
- Load package into a multi-agent runtime.
- Ontology-driven orchestration.
- Observability, audit logs, and safety guardrails.

5) Account & Identity
- Independent account system.
- Future federation (e.g., DingTalk).
- Initial test: separate accounts for Expert/Operator/End User.

6) Operations Console
- Expert directory and profiles.
- Package inventory, versions, usage metrics.
- Industry/sector classification.

7) Design
- Enterprise-grade, tech-forward UI.
- Reference enterprise SaaS patterns.

## Non-Functional
- Security, compliance, scalability, reliability.
- Full audit trail of expert input -> skill -> package -> runtime execution.
- Dockerized data stores (no local install).

## Recording
- All role outputs and process artifacts must be stored in docs/ and logs/.

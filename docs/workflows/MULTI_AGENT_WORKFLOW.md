# 多智能体协作流程（初稿）

## 编排原则
- 主控（你 + OpenClaw）拆解任务并分派
- 可并行先并行，依赖项再汇总
- 关键节点由质量门控（Reality Checker）审核

## 默认流程（OEAP）
1) 产品目标确认（Product Manager）
2) 流程与知识沉淀（Workflow Architect）
3) 服务拆分与数据模型（Backend Architect）
4) 架构模式与集成（Software Architect）
5) MCP 标准与适配（MCP Builder）
6) LangGraph 运行时方案（AI Engineer）
7) 安全与可靠性（Security Engineer + SRE）
8) 质量验收（Reality Checker）

## 输出要求
- 每个角色输出必须落盘到 docs/
- 主控汇总为最终交付包
- 所有过程写入 docs/WORKLOG.md

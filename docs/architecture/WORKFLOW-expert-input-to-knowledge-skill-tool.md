# WORKFLOW：专家输入 → 知识/技能/工具沉淀

**Version**: 0.1
**Date**: 2026-03-17
**Author**: Workflow Architect
**Status**: Draft

## Overview
将专家输入转化为可复用知识资产、技能方法与工具化资产；确保每个输入可追溯、可验证、可复用。

## Actors
- 专家、需求分析员、领域架构师、知识工程师、工具工程师、质量官

## Trigger
- 专家评审会议结束 / 专家提交书面建议 / 关键需求澄清完成

## Workflow Tree
### STEP 1：采集与记录
- 记录专家输入，建立输入条目
- 失败：缺字段/超时 → 补录/升报

### STEP 2：初筛与去噪
- 去掉无关信息，标注主题与阶段
- 失败：无有效内容/重复 → 归档/合并

### STEP 3：知识抽象与结构化
- 抽象为知识点/方法/约束/指标
- 失败：语义不清/超期 → 专家澄清/质控介入

### STEP 4：资产类型判定
- 判断：知识 / 技能 / 工具
- 失败：类型冲突 → 复核

### STEP 5A：知识资产入库
### STEP 5B：技能资产沉淀
### STEP 5C：工具资产封装

### STEP 6：质量审核与入库
- 合规性、可复用性、可追溯性
- 失败：质量拒绝 → 退回修订

### STEP 7：版本化发布
- 生成版本号、发布、索引更新

## State Transitions
recorded → triaged → structured → typed → drafted → approved → published

## Assumptions
- 专家可在 72h 内澄清

## Open Questions
- 资产库是否支持多版本并行？

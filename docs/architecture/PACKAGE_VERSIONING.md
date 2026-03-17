# 产品包 Git 版本化方案

## 目标
- 统一产品包的目录结构、清单（manifest）、变更记录（changelog）与 Git tag 规则。
- 支持后端 API 进行创建、发布与回滚的流程编排（先落骨架，后接入真实 Git/仓储）。

## 包目录结构生成器（默认布局）
```
<package-root>/
├─ manifest.yml
├─ CHANGELOG.md
├─ README.md
├─ LICENSE
├─ src/
├─ assets/
├─ configs/
├─ docs/
├─ tests/
└─ scripts/
```
- 生成器用于创建新包时输出建议目录与文件清单。
- 可在后续阶段根据包类型（模型包/流程包/前端包）扩展成多模板策略。

## manifest 规则（v1）
**必填字段**
- `name`：包名（kebab-case）
- `version`：语义化版本号（MAJOR.MINOR.PATCH）
- `owner`：负责人或团队标识
- `domain`：业务域
- `description`：简述

**可选字段**
- `dependencies`、`runtime`、`license`、`repository`、`tags`

**约束**
- `name`：小写 + 短横线
- `version`：SemVer
- `owner`：组织/团队 slug

## CHANGELOG 规则
- 格式遵循 **Keep a Changelog**。
- 必含 sections：`Added`、`Changed`、`Deprecated`、`Removed`、`Fixed`、`Security`。
- 版本头格式：
  ```
  ## [${version}] - ${date}
  ```

## Git Tag 规则
- 模式：`pkg/${name}/v${version}`
- 示例：`pkg/vision-insights/v0.1.0`
- 标签不可变（immutable），发布后不可覆盖；回滚以新版本/tag 标记。

## 后端 API 骨架
> 仅提供骨架与规则输出，后续可对接 Git 真实仓储操作与权限校验。

### 创建包
`POST /api/v1/packages`

Request:
```json
{
  "name": "vision-insights",
  "owner": "ai-platform",
  "domain": "vision",
  "description": "视觉分析包",
  "version": "0.1.0"
}
```

Response:
- 返回 `packageId`、`status=DRAFT`
- 附带目录结构、manifest/changelog/tag 规则

### 发布包
`POST /api/v1/packages/{id}/publish`

Request:
```json
{
  "name": "vision-insights",
  "version": "0.1.0",
  "changelogSummary": "init release"
}
```

Response:
- 返回 `status=PUBLISHED` 与 tag

### 回滚包
`POST /api/v1/packages/{id}/rollback`

Request:
```json
{
  "name": "vision-insights",
  "fromVersion": "0.1.1",
  "targetVersion": "0.1.0",
  "reason": "hotfix regression"
}
```

Response:
- 返回 `status=ROLLED_BACK` 与版本范围

## 安全与权限建议（待接入）
- 发布/回滚权限需绑定包 owner/team。
- Git tag 与发布流水线需要审计日志（操作人、时间、变更摘要）。
- manifest/changelog 校验在 CI 中强制执行。

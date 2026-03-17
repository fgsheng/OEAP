# RBAC/ABAC 与审计链路接入方案（Security Engineer）

## 1. 目标与边界
- **目标**：统一访问控制（RBAC/ABAC）与审计链路，覆盖 API 网关 → 业务服务 → 数据层。
- **边界**：
  - 访问控制仅授权，认证由 IdP/OIDC/JWT 提供。
  - 审计链路需满足不可抵赖、可追溯、可检索。

## 2. 模型设计
### 2.1 RBAC
- 实体：User、Role、Permission
- 关系：User↔Role、Role↔Permission
- Permission：`resource:action`（如 `project:read`）

### 2.2 ABAC
- 主体属性：userId、department、tenantId、clearanceLevel
- 资源属性：ownerId、classification、tenantId、status
- 环境属性：ip、deviceTrust、time、geo
- 规则示例：`subject.tenantId == resource.tenantId AND subject.department IN resource.allowedDepartments`

### 2.3 混合模型
- 决策顺序：RBAC → ABAC
- 默认策略：显式允许，默认拒绝

## 3. 架构与链路
- PDP（策略引擎）/ PEP（网关中间件）/ PIP（属性来源）
- API 网关解析 JWT → PEP 调 PDP → allow/deny + policyId → 触发审计

## 4. 后端接口建议
### RBAC
- `POST /admin/roles`
- `POST /admin/roles/{roleId}/permissions`
- `POST /admin/users/{userId}/roles`
- `GET /admin/roles`
- `GET /admin/permissions`

### ABAC
- `POST /admin/policies`
- `PUT /admin/policies/{policyId}`
- `GET /admin/policies`
- `POST /admin/policies/{policyId}/test`

### 授权判定
- `POST /authz/decision`

### 审计事件
- `POST /audit/events`

## 5. 数据结构建议
- roles / permissions / role_permissions / user_roles
- policies（JSON/Regо 规则）
- audit_events（hash chain）

## 6. 安全与合规
- 审计日志追加写、不可篡改（WORM）
- 敏感字段脱敏
- 审计日志仅安全管理员可读

## 7. 落地步骤
1) 接入 PEP
2) 部署 PDP
3) RBAC API
4) ABAC API
5) 审计链路落地
6) 监控与告警

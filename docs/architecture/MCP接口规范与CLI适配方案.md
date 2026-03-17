# MCP接口规范与CLI→MCP适配方案（MCP Builder）

## 1) 接口规范（MCP）
**目标**：将 CLI 能力以 MCP 工具形式暴露，保证可发现、可验证、可审计。

**工具命名**
- 动词+名词：`list_projects`、`run_build`、`get_log`
- 语义清晰，避免通用名：`execute`、`query1`

**参数与校验**
- 使用 Zod 类型约束
- 输入最小化：仅允许必需参数
- 安全参数：对路径、命令、标志进行白名单/枚举校验

**输出结构**
- 数据返回 JSON（便于代理理解）
- 人类可读说明可用 Markdown 作为补充
- 错误必须结构化：`{ error: { code, message, hint } }`

**示例（简化）**
```ts
server.tool("list_projects", { path: z.string().default(".") }, async ({ path }) => {
  const data = await listProjects(path);
  return { content: [{ type: "text", text: JSON.stringify(data) }] };
});
```

---

## 2) 权限与审计
**最小权限原则**
- CLI 仅允许安全子命令
- 对路径限制在允许目录（sandbox）
- 禁止任意 shell 拼接

**审计记录**
- 记录每次调用：工具名、参数、调用者、时间、返回状态
- 错误与拒绝也要记录

**安全策略建议**
- 使用 allowlist 命令 + 参数枚举
- 使用执行超时、输出大小上限
- 敏感字段脱敏记录

---

## 3) CLI → MCP 适配流程
1. **CLI 能力梳理**：列出命令、参数、输出、风险
2. **设计 MCP 工具**：工具名、参数 schema、输出 JSON 结构
3. **封装执行层**：统一执行器（超时、stdout/stderr 捕获）
4. **权限控制**：命令白名单、路径约束、环境隔离
5. **返回格式标准化**：成功 JSON、失败 error 结构
6. **测试与验收**：功能、权限、异常、回归

---

## 4) 示例（CLI → MCP）
**CLI：**
```
mycli projects list --json
```

**MCP Tool：**
```ts
server.tool("list_projects", {
  format: z.enum(["json"]).default("json")
}, async () => {
  const { stdout } = await runSafeCli(["mycli", "projects", "list", "--json"]);
  return { content: [{ type: "text", text: stdout }] };
});
```

---

## 5) 测试策略
**单元测试**
- 参数校验（必填、范围、枚举）
- CLI 执行器（超时、stderr 处理）

**集成测试**
- 真实 CLI 调用（mock 或容器隔离）
- 权限拒绝路径覆盖

**安全测试**
- 命令注入
- 非法路径访问
- 输出过大/超时

**回归测试**
- 核心工具用例快照
- CLI 升级后兼容性检查

---

如需补充“完整 MCP server 代码模板”或“可执行适配脚手架”，可继续扩展。

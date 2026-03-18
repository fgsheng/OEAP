# Frontend 最终集成收口说明

## 目标与范围
- 完成平台前端的最终集成（React + Vite）。
- 以可运行的 UI 壳与页面结构为主，先行输出一致的视觉/交互框架。

## 交付内容
- 前端工程骨架与路由结构（`AppShell + Routes`）。
- 核心页面：平台概览、专家输入、产品包、运行态/运行执行等。
- 统一 UI 组件与布局规范（Card、PageHeader、Sidebar、Topbar）。
- 设计 token 与全局样式基线（`src/styles`）。
- `services/frontend/README.md`：启动方式占位、目录结构、Mock/Live 说明。

## Mock → Live 接入策略
- 现阶段页面使用静态数据进行 UI 占位与交互演示。
- 下一步接入后端接口时，新增 `src/services/`（或 `src/api/`）统一管理请求。
- 使用 `VITE_API_BASE_URL` 等环境变量区分 dev/staging/prod。

## 风险与注意事项
- 暂无后端契约时，页面字段与排序为 UI 预设；需在联调时对齐接口 schema。
- 运行态与产品包模块后续可能引入更复杂的状态管理（建议再评估是否上状态库）。

## 后续待办（建议）
- 完成接口契约文档与前端调用示例。
- 增加 loading/empty/error 等状态组件。
- 路由权限与登录态接入（如需）。

---
**状态**：Final UI ready / waiting for API integration

# OEAP Frontend

企业级智能平台前端（React + Vite）。当前以静态样例数据驱动页面结构，作为后端接口联调前的 UI 先行版本。

## 启动方式（占位）

> TODO：补充本地依赖、环境变量与运行命令

可参考脚本：
- `npm run dev`：本地开发
- `npm run build`：构建
- `npm run preview`：预览

## 目录结构

```
services/frontend
├─ public/               # 静态资源（如有）
├─ src/
│  ├─ app/               # 应用壳与全局布局
│  ├─ components/        # 组件库
│  │  ├─ layout/         # 布局类组件
│  │  └─ ui/             # 通用 UI 组件
│  ├─ pages/             # 页面视图
│  ├─ styles/            # 全局样式与设计变量
│  ├─ App.jsx            # 路由入口
│  └─ main.jsx           # 渲染入口
├─ index.html
├─ package.json
├─ vite.config.js
└─ README.md
```

## Mock / Live 数据说明

- **Mock（当前）**：页面数据来自组件内静态数组（见 `src/pages/*`），用于快速完成视觉与交互闭环。
- **Live（待接入）**：后续接入后端 API 时，建议新增 `src/services/` 或 `src/api/` 统一封装请求；
  并通过环境变量（例如 `VITE_API_BASE_URL`）配置不同环境的接口地址。

> TODO：补充接口契约与数据流接入说明

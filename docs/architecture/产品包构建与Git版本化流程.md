# 产品包构建与 Git 版本化流程（草案）

## 1) 构建流程
1. 选择专家输入 sourceId
2. 生成 ontology / skills / mcp
3. 生成 package.yaml
4. 写入 Git 仓库
5. 打 tag + changelog

## 2) 基础 API
- POST /api/v1/package-builder
  - 请求：manifest + sourceId
  - 响应：packageId + status

## 3) Git 版本化
- 标签规则：v{major}.{minor}.{patch}
- changelog 记录变更内容

import React from 'react'
import { Link } from 'react-router-dom'
import PageHeader from '../components/ui/PageHeader'
import Card from '../components/ui/Card'

const assetCards = [
  {
    title: '知识库卡片',
    description: '企业语料、政策、流程 SOP 汇聚。'
  },
  {
    title: '技能与工作流',
    description: '多步推理与工具调用编排。'
  },
  {
    title: '工具连接器',
    description: 'API、RPA、插件式能力整合。'
  }
]

const components = ['AssetCard', 'PackBuilder', 'VersionTimeline', 'ReleaseChecklist']

export default function ProductPack() {
  return (
    <div className="page">
      <PageHeader
        title="产品包构建"
        subtitle="将知识、技能、工具组合成可交付的产品包。"
        meta={components.map((item) => (
          <span key={item} className="badge">
            {item}
          </span>
        ))}
      />

      <section className="section">
        <div className="card-grid">
          {assetCards.map((item) => (
            <Card key={item.title} title={item.title} description={item.description} />
          ))}
        </div>
      </section>

      <section className="section" style={{ marginTop: 20 }}>
        <div className="split-grid">
          <Card title="发布流程">
            <ul className="list">
              <li>资产打包与版本生成</li>
              <li>审批/审计规则挂载</li>
              <li>运行时目标环境绑定</li>
            </ul>
          </Card>
          <Card
            title="产品包详情骨架"
            description="进入单包详情页查看依赖、版本与发布节奏。"
          >
            <Link className="button" to="/product-pack/detail">
              查看详情页
            </Link>
          </Card>
        </div>
      </section>
    </div>
  )
}

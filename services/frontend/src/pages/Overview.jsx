import React from 'react'
import PageHeader from '../components/ui/PageHeader'
import Card from '../components/ui/Card'

const metrics = [
  { label: '活跃专家', value: '18' },
  { label: '资产包版本', value: '42' },
  { label: '运行态实例', value: '7' },
  { label: '审计规则', value: '12' }
]

const roadmap = [
  '专家输入 → 结构化知识',
  '知识/技能/工具资产化',
  '产品包编排与发布',
  '运行时可观测与治理'
]

const components = ['AppShell', 'Sidebar', 'Topbar', 'PageHeader', 'Card']

export default function Overview() {
  return (
    <div className="page">
      <PageHeader
        title="平台概览"
        subtitle="掌握资产流转、运行态与治理的整体健康度。"
        meta={components.map((item) => (
          <span key={item} className="badge">
            {item}
          </span>
        ))}
      />

      <section className="section">
        <div className="card-grid">
          {metrics.map((metric) => (
            <Card key={metric.label} title={metric.value} description={metric.label} />
          ))}
        </div>
      </section>

      <section className="section" style={{ marginTop: 20 }}>
        <Card
          title="MVP 流转路径"
          description="端到端链路拆解，便于快速落地前端与后端接口对齐。"
        >
          <ul className="list">
            {roadmap.map((item) => (
              <li key={item}>{item}</li>
            ))}
          </ul>
        </Card>
      </section>
    </div>
  )
}

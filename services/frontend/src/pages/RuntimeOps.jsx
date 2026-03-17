import React from 'react'
import PageHeader from '../components/ui/PageHeader'
import Card from '../components/ui/Card'

const runtimeBlocks = [
  { title: '实例健康度', description: '延迟、成功率、成本追踪。' },
  { title: '策略控制', description: '访问控制、速率限制与配额。' },
  { title: '可观测性', description: 'Tracing、审计日志、告警订阅。' }
]

const components = ['RuntimeStatus', 'PolicyBoard', 'AuditStream', 'CostPanel']

export default function RuntimeOps() {
  return (
    <div className="page">
      <PageHeader
        title="运行时运营"
        subtitle="上线后的稳定性、治理与成本可视化。"
        meta={components.map((item) => (
          <span key={item} className="badge">
            {item}
          </span>
        ))}
      />

      <section className="section">
        <div className="card-grid">
          {runtimeBlocks.map((item) => (
            <Card key={item.title} title={item.title} description={item.description} />
          ))}
        </div>
      </section>

      <section className="section" style={{ marginTop: 20 }}>
        <Card title="待接入数据源">
          <ul className="list">
            <li>指标：/api/v1/runtime/metrics</li>
            <li>告警：/api/v1/runtime/alerts</li>
            <li>审计：/api/v1/runtime/audit</li>
          </ul>
        </Card>
      </section>
    </div>
  )
}

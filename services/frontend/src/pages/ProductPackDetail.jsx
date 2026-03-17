import React from 'react'
import PageHeader from '../components/ui/PageHeader'
import Card from '../components/ui/Card'

const overviewCards = [
  { title: '产品包编号', description: 'PK-2026-001-A' },
  { title: '业务域', description: '智能客服 · 订单履约' },
  { title: '当前版本', description: 'v1.3.2 (已发布)' },
  { title: '部署区域', description: '华东 / 新加坡双活' }
]

const dependencies = [
  '知识库：CustomerCare-KB-v4',
  '工作流：Order-Fallback-Routing',
  '工具：CRM + RPA Connector',
  '策略：敏感字段脱敏策略 v2'
]

const releaseTimeline = [
  { step: '需求冻结', time: '03-10 14:00', status: '完成' },
  { step: '安全评审', time: '03-12 11:30', status: '完成' },
  { step: '发布窗口', time: '03-18 22:00', status: '待执行' }
]

export default function ProductPackDetail() {
  return (
    <div className="page">
      <PageHeader
        title="产品包详情"
        subtitle="单个产品包的配置、依赖与发布状态全景。"
        meta={['PackOverview', 'DependencyGraph', 'ReleaseStatus'].map((item) => (
          <span key={item} className="badge">
            {item}
          </span>
        ))}
      />

      <section className="section">
        <div className="card-grid">
          {overviewCards.map((item) => (
            <Card key={item.title} title={item.title} description={item.description} />
          ))}
        </div>
      </section>

      <section className="section" style={{ marginTop: 20 }}>
        <div className="split-grid">
          <Card title="依赖资产" description="当前版本依赖的知识与工具清单。">
            <ul className="list" style={{ marginTop: 8 }}>
              {dependencies.map((item) => (
                <li key={item}>{item}</li>
              ))}
            </ul>
          </Card>
          <Card title="发布节奏" description="版本发布的关键里程碑。">
            <div className="stack" style={{ marginTop: 8 }}>
              {releaseTimeline.map((item) => (
                <div key={item.step} className="panel-row">
                  <div className="panel-row__title">{item.step}</div>
                  <span className="table-mono">{item.time}</span>
                  <span className="badge">{item.status}</span>
                </div>
              ))}
            </div>
          </Card>
        </div>
      </section>
    </div>
  )
}

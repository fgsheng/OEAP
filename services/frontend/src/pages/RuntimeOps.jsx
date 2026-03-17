import React from 'react'
import { Link } from 'react-router-dom'
import PageHeader from '../components/ui/PageHeader'
import Card from '../components/ui/Card'

const runtimeBlocks = [
  { title: '实例健康度', description: '延迟、成功率、成本追踪。' },
  { title: '策略控制', description: '访问控制、速率限制与配额。' },
  { title: '可观测性', description: 'Tracing、审计日志、告警订阅。' }
]

const opsSources = [
  {
    name: '实时指标',
    endpoint: '/api/v1/runtime/metrics',
    owner: 'SRE',
    status: '待联调'
  },
  {
    name: '告警事件',
    endpoint: '/api/v1/runtime/alerts',
    owner: '平台运营',
    status: '对接中'
  },
  {
    name: '审计日志',
    endpoint: '/api/v1/runtime/audit',
    owner: '安全治理',
    status: '待联调'
  }
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
        <div className="split-grid">
          <Card
            title="运营控制台联调占位"
            description="运营侧数据接入进度与负责人统一看板。"
          >
            <div className="table" style={{ marginTop: 12 }}>
              <div className="table-row table-head">
                <span>数据域</span>
                <span>接口</span>
                <span>负责人</span>
                <span>状态</span>
              </div>
              {opsSources.map((item) => (
                <div key={item.name} className="table-row">
                  <span>{item.name}</span>
                  <span className="table-mono">{item.endpoint}</span>
                  <span>{item.owner}</span>
                  <span className="badge">{item.status}</span>
                </div>
              ))}
            </div>
          </Card>
          <Card
            title="运行时执行页骨架"
            description="展示执行链路、资源与事件流的运行时视图。"
          >
            <Link className="button" to="/runtime/execution">
              查看执行页
            </Link>
          </Card>
        </div>
      </section>
    </div>
  )
}

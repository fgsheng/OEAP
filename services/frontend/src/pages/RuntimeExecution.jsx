import React from 'react'
import PageHeader from '../components/ui/PageHeader'
import Card from '../components/ui/Card'

const executionStats = [
  { title: '当前运行实例', description: '48' },
  { title: '平均延迟', description: '820ms' },
  { title: '成功率', description: '99.12%' },
  { title: '成本/千次', description: '¥12.6' }
]

const pipelineSteps = [
  {
    name: '输入解析',
    detail: '表单/语音/接口输入规范化',
    status: '运行中'
  },
  {
    name: '知识检索',
    detail: '向量检索 + 权限过滤',
    status: '运行中'
  },
  {
    name: '工具执行',
    detail: '调用 CRM / 工单系统',
    status: '等待中'
  },
  {
    name: '结果生成',
    detail: '模型生成与合规校验',
    status: '待执行'
  }
]

const logEntries = [
  '18:01:22 订单核验节点完成：匹配到 124 条记录',
  '18:01:24 调用 CRM Connector，等待响应',
  '18:01:27 告警：API 响应延迟超过阈值',
  '18:01:29 自动切换至备份节点'
]

export default function RuntimeExecution() {
  return (
    <div className="page">
      <PageHeader
        title="运行时执行"
        subtitle="实时执行链路与资源利用情况概览。"
        meta={['ExecutionMap', 'LiveTrace', 'ResourceLedger'].map((item) => (
          <span key={item} className="badge">
            {item}
          </span>
        ))}
      />

      <section className="section">
        <div className="card-grid">
          {executionStats.map((item) => (
            <Card key={item.title} title={item.title} description={item.description} />
          ))}
        </div>
      </section>

      <section className="section" style={{ marginTop: 20 }}>
        <div className="split-grid">
          <Card title="执行链路" description="关键步骤状态与耗时占位。">
            <div className="stack" style={{ marginTop: 8 }}>
              {pipelineSteps.map((item) => (
                <div key={item.name} className="panel-row">
                  <div className="panel-row__title">{item.name}</div>
                  <span className="panel-row__desc">{item.detail}</span>
                  <span className="badge">{item.status}</span>
                </div>
              ))}
            </div>
          </Card>
          <Card title="实时事件流" description="运行时执行日志、告警与切换。">
            <div className="stack" style={{ marginTop: 8 }}>
              {logEntries.map((item) => (
                <div key={item} className="panel">
                  <span className="table-mono">{item}</span>
                </div>
              ))}
            </div>
          </Card>
        </div>
      </section>
    </div>
  )
}

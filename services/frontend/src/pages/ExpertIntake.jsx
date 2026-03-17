import React from 'react'
import PageHeader from '../components/ui/PageHeader'
import Card from '../components/ui/Card'

const sections = [
  {
    title: '业务目标采集',
    description: '引导式问答 + 业务上下文标签化。'
  },
  {
    title: '流程与触发条件',
    description: '结构化字段、流程图、风险标注。'
  },
  {
    title: '输入/输出约束',
    description: '字段校验、数据源映射、权限要求。'
  }
]

const components = [
  'ExpertIntakeForm',
  'TriggerList',
  'ValidationPanel',
  'AI Gap Detector'
]

export default function ExpertIntake() {
  return (
    <div className="page">
      <PageHeader
        title="专家输入"
        subtitle="把业务专家的经验固化为可执行的结构化资产。"
        meta={components.map((item) => (
          <span key={item} className="badge">
            {item}
          </span>
        ))}
      />

      <section className="section">
        <div className="card-grid">
          {sections.map((item) => (
            <Card key={item.title} title={item.title} description={item.description} />
          ))}
        </div>
      </section>

      <section className="section" style={{ marginTop: 20 }}>
        <Card title="缺失项检测">
          <p style={{ color: 'var(--color-text-secondary)', marginTop: 6 }}>
            对接 /api/v1/expert/intake，显示缺失字段、改进建议和风险提示。
          </p>
          <div className="badge">接口待联调</div>
        </Card>
      </section>
    </div>
  )
}

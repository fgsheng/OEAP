import React, { useMemo, useState } from 'react'
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

const initialMissingItems = [
  {
    id: 'goal',
    label: '业务目标与成功指标',
    severity: '高',
    impact: '缺少量化目标会导致策略难以收敛。',
    question: '请描述本次流程的业务目标、成功指标与期望周期。'
  },
  {
    id: 'trigger',
    label: '触发条件',
    severity: '中',
    impact: '触发边界不清晰，容易产生误触发。',
    question: '触发条件是什么？是否需要时间窗口或阈值限制？'
  },
  {
    id: 'data',
    label: '关键数据源',
    severity: '高',
    impact: '缺少数据源映射会影响输出可信度。',
    question: '请列出需要接入的数据源、字段范围和权限要求。'
  }
]

export default function ExpertIntake() {
  const [missingItems, setMissingItems] = useState(initialMissingItems)
  const [selectedIds, setSelectedIds] = useState(
    initialMissingItems.map((item) => item.id)
  )
  const [answers, setAnswers] = useState({})

  const followUpItems = useMemo(
    () => missingItems.filter((item) => selectedIds.includes(item.id)),
    [missingItems, selectedIds]
  )

  const handleToggle = (id) => {
    setSelectedIds((prev) =>
      prev.includes(id) ? prev.filter((item) => item !== id) : [...prev, id]
    )
  }

  const handleAnswerChange = (id, value) => {
    setAnswers((prev) => ({ ...prev, [id]: value }))
  }

  const handleResolve = (id) => {
    setMissingItems((prev) => prev.filter((item) => item.id !== id))
    setSelectedIds((prev) => prev.filter((item) => item !== id))
  }

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
        <div className="split-grid">
          <Card
            title="缺失项检测"
            description="对接 /api/v1/expert/intake，显示缺失字段、改进建议和风险提示。"
            footer={<span className="badge">接口待联调</span>}
          >
            <div className="stack" style={{ marginTop: 12 }}>
              {missingItems.map((item) => (
                <div key={item.id} className="panel-row">
                  <label className="panel-row__title">
                    <input
                      type="checkbox"
                      checked={selectedIds.includes(item.id)}
                      onChange={() => handleToggle(item.id)}
                    />
                    <span>{item.label}</span>
                  </label>
                  <span className={`pill pill--${item.severity}`}>{item.severity}</span>
                  <span className="panel-row__desc">{item.impact}</span>
                </div>
              ))}
              {missingItems.length === 0 && (
                <div className="empty-state">已完成全部缺失项补充。</div>
              )}
            </div>
          </Card>

          <Card title="回问清单" description="系统将缺失项自动转化为可执行的问题。">
            <div className="stack" style={{ marginTop: 12 }}>
              {followUpItems.map((item) => (
                <div key={item.id} className="panel">
                  <div className="panel__head">
                    <strong>{item.question}</strong>
                    <span className={`pill pill--${item.severity}`}>{item.severity}</span>
                  </div>
                  <textarea
                    className="text-area"
                    placeholder="记录专家补充内容..."
                    value={answers[item.id] || ''}
                    onChange={(event) =>
                      handleAnswerChange(item.id, event.target.value)
                    }
                  />
                  <button
                    className="button"
                    type="button"
                    onClick={() => handleResolve(item.id)}
                  >
                    标记已补充
                  </button>
                </div>
              ))}
              {followUpItems.length === 0 && (
                <div className="empty-state">暂无需要回问的问题。</div>
              )}
            </div>
          </Card>
        </div>
      </section>
    </div>
  )
}

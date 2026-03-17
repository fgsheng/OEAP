import React, { useState } from 'react'

export default function App() {
  const [form, setForm] = useState({
    goal: '',
    scope: '',
    triggers: '',
    inputs: '',
    outputs: '',
    steps: ''
  })
  const [result, setResult] = useState(null)

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const submit = async () => {
    const payload = {
      goal: form.goal,
      scope: form.scope,
      triggers: form.triggers.split('\n').filter(Boolean),
      inputs: form.inputs.split('\n').filter(Boolean),
      outputs: form.outputs.split('\n').filter(Boolean),
      steps: form.steps.split('\n').filter(Boolean)
    }
    const res = await fetch('/api/v1/expert/intake', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    })
    const data = await res.json()
    setResult(data)
  }

  return (
    <div style={{ fontFamily: 'Inter, system-ui, sans-serif', padding: 24 }}>
      <h1>OEAP 企业级智能平台</h1>
      <p>专家输入 → 知识/技能/工具 → 产品包 → 运行时</p>

      <section style={{ marginTop: 24 }}>
        <h2>专家输入（引导式 - MVP）</h2>
        <div style={{ display: 'grid', gap: 12, maxWidth: 720 }}>
          <input name="goal" placeholder="业务目标" value={form.goal} onChange={handleChange} />
          <input name="scope" placeholder="适用边界" value={form.scope} onChange={handleChange} />
          <textarea name="triggers" placeholder="触发条件（每行一条）" rows={3} value={form.triggers} onChange={handleChange} />
          <textarea name="inputs" placeholder="输入（每行一条）" rows={3} value={form.inputs} onChange={handleChange} />
          <textarea name="outputs" placeholder="输出（每行一条）" rows={3} value={form.outputs} onChange={handleChange} />
          <textarea name="steps" placeholder="流程步骤（每行一条）" rows={4} value={form.steps} onChange={handleChange} />
          <button onClick={submit}>提交并检测缺失项</button>
        </div>

        {result && (
          <div style={{ marginTop: 16 }}>
            <h3>结果</h3>
            <pre>{JSON.stringify(result, null, 2)}</pre>
          </div>
        )}
      </section>
    </div>
  )
}

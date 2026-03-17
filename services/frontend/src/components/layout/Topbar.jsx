import React from 'react'

export default function Topbar() {
  return (
    <header className="topbar">
      <div className="section">
        <strong>AI 平台控制台</strong>
        <span style={{ color: 'var(--color-text-secondary)', fontSize: 12 }}>
          统一资产 · 运行态 · 治理
        </span>
      </div>
      <div style={{ display: 'flex', gap: 12, alignItems: 'center' }}>
        <span className="badge">环境：Staging</span>
        <button
          style={{
            background: 'var(--color-accent)',
            color: '#0b0f17',
            border: 'none',
            borderRadius: 999,
            padding: '8px 16px',
            fontWeight: 600
          }}
        >
          新建资产
        </button>
      </div>
    </header>
  )
}

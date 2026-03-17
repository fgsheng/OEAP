import React from 'react'

export default function PageHeader({ title, subtitle, meta }) {
  return (
    <div className="page-header">
      <h1 style={{ margin: 0 }}>{title}</h1>
      {subtitle && (
        <p style={{ margin: 0, color: 'var(--color-text-secondary)' }}>
          {subtitle}
        </p>
      )}
      {meta && (
        <div style={{ display: 'flex', gap: 8, flexWrap: 'wrap' }}>{meta}</div>
      )}
    </div>
  )
}

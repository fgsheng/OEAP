import React from 'react'

export default function Card({ title, description, children, footer }) {
  return (
    <div className="card">
      {title && <strong>{title}</strong>}
      {description && (
        <p style={{ color: 'var(--color-text-secondary)', marginTop: 6 }}>
          {description}
        </p>
      )}
      {children}
      {footer && <div style={{ marginTop: 12 }}>{footer}</div>}
    </div>
  )
}

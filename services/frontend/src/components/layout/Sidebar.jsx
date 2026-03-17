import React from 'react'
import { NavLink } from 'react-router-dom'

const navItems = [
  { label: '概览', to: '/overview' },
  { label: '专家输入', to: '/intake' },
  { label: '产品包', to: '/product-pack' },
  { label: '运行时', to: '/runtime' }
]

export default function Sidebar() {
  return (
    <aside className="sidebar">
      <div>
        <div className="sidebar__brand">OEAP 企业级智能平台</div>
        <div className="badge" style={{ marginTop: 8 }}>
          MVP 路线图
        </div>
      </div>
      <nav className="sidebar__nav">
        {navItems.map((item) => (
          <NavLink
            key={item.to}
            to={item.to}
            className={({ isActive }) =>
              `sidebar__link${isActive ? ' active' : ''}`
            }
          >
            {item.label}
          </NavLink>
        ))}
      </nav>
      <div className="section">
        <div className="badge">上线准备</div>
        <span style={{ color: 'var(--color-text-secondary)', fontSize: 12 }}>
          访问控制 · 监控 · 审计
        </span>
      </div>
    </aside>
  )
}

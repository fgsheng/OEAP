import React from 'react'
import { Outlet } from 'react-router-dom'
import Sidebar from '../components/layout/Sidebar'
import Topbar from '../components/layout/Topbar'

export default function AppShell() {
  return (
    <div className="app-shell">
      <Sidebar />
      <div className="app-shell__content">
        <Topbar />
        <main>
          <Outlet />
        </main>
      </div>
    </div>
  )
}

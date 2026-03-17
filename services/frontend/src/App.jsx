import React from 'react'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import AppShell from './app/AppShell'
import Overview from './pages/Overview'
import ExpertIntake from './pages/ExpertIntake'
import ProductPack from './pages/ProductPack'
import ProductPackDetail from './pages/ProductPackDetail'
import RuntimeOps from './pages/RuntimeOps'
import RuntimeExecution from './pages/RuntimeExecution'

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<AppShell />}>
          <Route path="/" element={<Navigate to="/overview" replace />} />
          <Route path="/overview" element={<Overview />} />
          <Route path="/intake" element={<ExpertIntake />} />
          <Route path="/product-pack" element={<ProductPack />} />
          <Route path="/product-pack/detail" element={<ProductPackDetail />} />
          <Route path="/runtime" element={<RuntimeOps />} />
          <Route path="/runtime/execution" element={<RuntimeExecution />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

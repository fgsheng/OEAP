from __future__ import annotations

import json
from dataclasses import dataclass
from datetime import datetime
from pathlib import Path
from typing import Any, Dict, List

from textual.app import App, ComposeResult
from textual.containers import Container, VerticalScroll
from textual.screen import Screen
from textual.widgets import Button, Footer, Header, Input, Label, Static, TextArea


DATA_DIR = Path(__file__).parent / "data"


@dataclass
class FieldSpec:
    key: str
    label: str
    placeholder: str = ""
    required: bool = False
    multiline: bool = False


@dataclass
class StepSpec:
    key: str
    title: str
    description: str
    fields: List[FieldSpec]


FLOW_DEFINITIONS: Dict[str, Dict[str, Any]] = {
    "expert_deposit": {
        "title": "专家沉淀",
        "steps": [
            StepSpec(
                key="expert_profile",
                title="专家画像",
                description="沉淀专家角色与履历，确保知识来源可靠。",
                fields=[
                    FieldSpec("expert_name", "专家姓名", required=True),
                    FieldSpec("expert_role", "专家角色/岗位", required=True),
                    FieldSpec("expert_domain", "核心领域", required=True),
                    FieldSpec("expert_experience", "经验年限/代表项目", multiline=True),
                ],
            ),
            StepSpec(
                key="knowledge_scope",
                title="知识范围",
                description="明确本次沉淀的范围与边界。",
                fields=[
                    FieldSpec("scope", "覆盖范围", required=True, multiline=True),
                    FieldSpec("out_of_scope", "不覆盖内容", multiline=True),
                    FieldSpec("success_signals", "成功信号/验收标准", required=True, multiline=True),
                ],
            ),
            StepSpec(
                key="workflow",
                title="最佳实践流程",
                description="描述专家流程步骤与关键决策点。",
                fields=[
                    FieldSpec("workflow_steps", "关键步骤", required=True, multiline=True),
                    FieldSpec("decision_points", "关键决策点", multiline=True),
                    FieldSpec("tools_systems", "常用工具/系统", multiline=True),
                ],
            ),
            StepSpec(
                key="assets",
                title="可复用资产",
                description="沉淀可复用的模板、话术与产出物。",
                fields=[
                    FieldSpec("templates", "模板/文档/表格", multiline=True),
                    FieldSpec("scripts", "话术/提示语", multiline=True),
                    FieldSpec("deliverables", "最终输出物", required=True, multiline=True),
                ],
            ),
        ],
    },
    "real_user_run": {
        "title": "真实用户运行",
        "steps": [
            StepSpec(
                key="context",
                title="运行上下文",
                description="收集用户当前场景与背景。",
                fields=[
                    FieldSpec("user_role", "用户角色", required=True),
                    FieldSpec("industry", "行业/业务领域", required=True),
                    FieldSpec("current_pain", "当前痛点", required=True, multiline=True),
                ],
            ),
            StepSpec(
                key="goal",
                title="目标与预期",
                description="明确用户期望与衡量方式。",
                fields=[
                    FieldSpec("goal", "目标/任务描述", required=True, multiline=True),
                    FieldSpec("success_metric", "成功指标", required=True, multiline=True),
                    FieldSpec("timeline", "期望周期", multiline=True),
                ],
            ),
            StepSpec(
                key="data_system",
                title="数据与系统",
                description="了解可用的数据与系统集成情况。",
                fields=[
                    FieldSpec("data_sources", "数据来源", required=True, multiline=True),
                    FieldSpec("systems", "现有系统/工具", multiline=True),
                    FieldSpec("constraints", "约束/合规要求", multiline=True),
                ],
            ),
            StepSpec(
                key="run_plan",
                title="运行计划",
                description="定义试点范围与支持方式。",
                fields=[
                    FieldSpec("pilot_scope", "试点范围", required=True, multiline=True),
                    FieldSpec("support_team", "支持团队/负责人", multiline=True),
                    FieldSpec("risk_notes", "风险与备选方案", multiline=True),
                ],
            ),
        ],
    },
    "sales_engineer_script": {
        "title": "销售工程师脚本",
        "steps": [
            StepSpec(
                key="discovery",
                title="客户发现",
                description="对齐客户背景与关键联系人。",
                fields=[
                    FieldSpec("customer_name", "客户名称", required=True),
                    FieldSpec("stakeholders", "关键联系人/角色", required=True, multiline=True),
                    FieldSpec("business_background", "业务背景", multiline=True),
                ],
            ),
            StepSpec(
                key="pain_points",
                title="痛点与机会",
                description="识别最迫切的问题与机会。",
                fields=[
                    FieldSpec("pain_points", "核心痛点", required=True, multiline=True),
                    FieldSpec("current_solution", "现有解决方案", multiline=True),
                    FieldSpec("impact", "影响/损失", multiline=True),
                ],
            ),
            StepSpec(
                key="decision",
                title="决策与预算",
                description="确认决策链与预算状态。",
                fields=[
                    FieldSpec("decision_process", "决策流程", required=True, multiline=True),
                    FieldSpec("budget", "预算范围", multiline=True),
                    FieldSpec("timeline", "决策时间线", multiline=True),
                ],
            ),
            StepSpec(
                key="technical",
                title="技术现状",
                description="快速摸清技术栈与集成难点。",
                fields=[
                    FieldSpec("systems", "现有系统", required=True, multiline=True),
                    FieldSpec("data_access", "数据获取方式", multiline=True),
                    FieldSpec("security", "安全/合规要求", multiline=True),
                ],
            ),
            StepSpec(
                key="next_step",
                title="下一步行动",
                description="明确试点与下一步计划。",
                fields=[
                    FieldSpec("pilot_plan", "试点计划", required=True, multiline=True),
                    FieldSpec("success_metrics", "成功指标", multiline=True),
                    FieldSpec("owner", "负责人", multiline=True),
                ],
            ),
        ],
    },
}


class HomeScreen(Screen):
    BINDINGS = [("q", "quit", "退出")]

    def compose(self) -> ComposeResult:
        yield Header()
        yield Container(
            Static("Enterprise AI Platform CUI", id="title"),
            Static("请选择流程开始采集", id="subtitle"),
            Button("专家沉淀", id="expert_deposit", variant="primary"),
            Button("真实用户运行", id="real_user_run", variant="primary"),
            Button("销售工程师脚本", id="sales_engineer_script", variant="primary"),
            id="menu",
        )
        yield Footer()

    def on_button_pressed(self, event: Button.Pressed) -> None:
        flow_key = event.button.id
        if flow_key in FLOW_DEFINITIONS:
            flow = FLOW_DEFINITIONS[flow_key]
            self.app.push_screen(
                WizardScreen(flow_key=flow_key, title=flow["title"], steps=flow["steps"])
            )


class WizardScreen(Screen):
    BINDINGS = [("escape", "back", "返回"), ("ctrl+s", "save", "保存")]

    def __init__(self, flow_key: str, title: str, steps: List[StepSpec]) -> None:
        super().__init__()
        self.flow_key = flow_key
        self.title = title
        self.steps = steps
        self.index = 0
        self.data: Dict[str, Any] = {}
        self._fields: Dict[str, Any] = {}

    def compose(self) -> ComposeResult:
        yield Header()
        yield VerticalScroll(id="body")
        yield Static("", id="status")
        yield Container(
            Button("上一步", id="back", variant="default"),
            Button("下一步", id="next", variant="primary"),
            Button("保存并退出", id="save", variant="success"),
            id="controls",
        )
        yield Footer()

    def on_mount(self) -> None:
        self._render_step()

    def _render_step(self) -> None:
        body = self.query_one("#body", VerticalScroll)
        body.remove_children()
        step = self.steps[self.index]
        body.mount(Static(f"{self.title} · 第 {self.index + 1}/{len(self.steps)} 步", id="step-title"))
        body.mount(Static(step.description, id="step-desc"))
        body.mount(Static(""))
        self._fields = {}
        for field in step.fields:
            body.mount(Label(field.label))
            if field.multiline:
                widget = TextArea(field.placeholder)
                widget.border_title = field.placeholder or field.label
                widget.height = 4
                if field.key in self.data:
                    widget.text = str(self.data[field.key])
            else:
                widget = Input(placeholder=field.placeholder)
                if field.key in self.data:
                    widget.value = str(self.data[field.key])
            self._fields[field.key] = widget
            body.mount(widget)
        self._update_controls()

    def _update_controls(self) -> None:
        back_btn = self.query_one("#back", Button)
        next_btn = self.query_one("#next", Button)
        back_btn.disabled = self.index == 0
        if self.index == len(self.steps) - 1:
            next_btn.label = "完成"
        else:
            next_btn.label = "下一步"

    def _store_current(self) -> None:
        for key, widget in self._fields.items():
            if isinstance(widget, TextArea):
                value = widget.text.strip()
            else:
                value = widget.value.strip()
            if value:
                self.data[key] = value

    def _validate_current(self) -> bool:
        step = self.steps[self.index]
        errors = []
        for field in step.fields:
            if field.required:
                widget = self._fields[field.key]
                value = widget.text.strip() if isinstance(widget, TextArea) else widget.value.strip()
                if not value:
                    errors.append(field.label)
        status = self.query_one("#status", Static)
        if errors:
            status.update(f"必填项未完成：{'、'.join(errors)}")
            self.app.bell()
            return False
        status.update("")
        return True

    def _save_payload(self) -> Path:
        DATA_DIR.mkdir(parents=True, exist_ok=True)
        payload = {
            "flow": self.flow_key,
            "flow_title": self.title,
            "timestamp": datetime.now().isoformat(timespec="seconds"),
            "data": self.data,
            "steps": [step.key for step in self.steps],
        }
        filename = f"{self.flow_key}_{datetime.now().strftime('%Y%m%d_%H%M%S')}.json"
        path = DATA_DIR / filename
        path.write_text(json.dumps(payload, ensure_ascii=False, indent=2), encoding="utf-8")
        return path

    def on_button_pressed(self, event: Button.Pressed) -> None:
        if event.button.id == "back":
            self._store_current()
            if self.index > 0:
                self.index -= 1
                self._render_step()
            return
        if event.button.id == "next":
            if not self._validate_current():
                return
            self._store_current()
            if self.index < len(self.steps) - 1:
                self.index += 1
                self._render_step()
            else:
                path = self._save_payload()
                self.app.notify(f"已保存至 {path}")
                self.app.pop_screen()
            return
        if event.button.id == "save":
            if not self._validate_current():
                return
            self._store_current()
            path = self._save_payload()
            self.app.notify(f"已保存至 {path}")
            self.app.pop_screen()


class EnterpriseCUIApp(App):
    CSS = """
    #menu {
        width: 60%;
        height: auto;
        margin: 2 0;
        align: center middle;
    }

    #title {
        content-align: center middle;
        text-style: bold;
        padding: 1 0;
    }

    #subtitle {
        content-align: center middle;
        padding-bottom: 1;
        color: $text-muted;
    }

    #body {
        padding: 1 4;
    }

    #step-title {
        text-style: bold;
        padding-bottom: 1;
    }

    #status {
        color: $error;
        padding: 0 4;
    }

    #controls {
        dock: bottom;
        padding: 1 4;
        height: auto;
        align: right middle;
    }

    TextArea {
        border: tall $primary;
    }
    """

    def on_mount(self) -> None:
        self.push_screen(HomeScreen())


if __name__ == "__main__":
    EnterpriseCUIApp().run()

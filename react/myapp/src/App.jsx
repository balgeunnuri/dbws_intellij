import { useState } from "react";

const TAG_LABELS = { personal: "개인", work: "업무", health: "건강" };
const TAG_STYLES = {
  personal: { background: "#E6F1FB", color: "#185FA5" },
  work:     { background: "#EEEDFE", color: "#3C3489" },
  health:   { background: "#EAF3DE", color: "#3B6D11" },
};

const INITIAL_TODOS = [
  { id: 1, text: "아침 스트레칭 30분", done: false, tag: "health" },
  { id: 2, text: "주간 보고서 작성",   done: false, tag: "work"   },
  { id: 3, text: "장보기 목록 정리",   done: true,  tag: "personal" },
];

export default function TodoApp() {
  const [todos,  setTodos]  = useState(INITIAL_TODOS);
  const [input,  setInput]  = useState("");
  const [tag,    setTag]    = useState("");
  const [filter, setFilter] = useState("all");
  const [nextId, setNextId] = useState(4);

  const today = new Date().toLocaleDateString("ko-KR", {
    year: "numeric", month: "long", day: "numeric", weekday: "long",
  });

  /* ── actions ─────────────────────────────── */
  function addTodo() {
    const text = input.trim();
    if (!text) return;
    setTodos([{ id: nextId, text, done: false, tag }, ...todos]);
    setNextId(nextId + 1);
    setInput("");
    setTag("");
  }

  function toggle(id) {
    setTodos(todos.map(t => t.id === id ? { ...t, done: !t.done } : t));
  }

  function remove(id) {
    setTodos(todos.filter(t => t.id !== id));
  }

  function clearDone() {
    setTodos(todos.filter(t => !t.done));
  }

  /* ── derived ─────────────────────────────── */
  const filtered = todos.filter(t =>
      filter === "all"    ? true :
          filter === "done"   ? t.done :
              /* active */          !t.done
  );
  const activeCount = todos.filter(t => !t.done).length;

  /* ── styles ──────────────────────────────── */
  const s = {
    app:       { maxWidth: 800, margin: "0 auto", padding: "4rem 2rem", fontFamily: "'Inter', system-ui, sans-serif", textAlign: "left" },
    h1:        { fontSize: 48, fontWeight: 700, marginBottom: 12, color: "#111", letterSpacing: "-1px" },
    sub:       { fontSize: 18, color: "#666", marginBottom: "3rem", fontWeight: 500 },
    row:       { display: "flex", gap: 16, marginBottom: "2rem" },
    input:     { flex: 1, padding: "0 24px", height: 64, border: "2px solid #e5e5e5", borderRadius: 16, fontSize: 20, outline: "none", transition: "border-color 0.2s", boxShadow: "0 4px 6px rgba(0,0,0,0.02)" },
    select:    { height: 64, padding: "0 20px", border: "2px solid #e5e5e5", borderRadius: 16, fontSize: 18, color: "#444", cursor: "pointer", transition: "border-color 0.2s", boxShadow: "0 4px 6px rgba(0,0,0,0.02)", outline: "none", backgroundColor: "#fff" },
    addBtn:    { padding: "0 40px", height: 64, background: "#111", color: "#fff", border: "none", borderRadius: 16, fontSize: 20, fontWeight: 600, cursor: "pointer", transition: "transform 0.1s, background 0.2s", boxShadow: "0 8px 16px rgba(0,0,0,0.1)" },
    filters:   { display: "flex", gap: 12, marginBottom: "2rem" },
    filterBtn: (active) => ({
      padding: "12px 28px", borderRadius: 99, fontSize: 18, cursor: "pointer", fontWeight: active ? 600 : 500,
      border: "2px solid", borderColor: active ? "#111" : "#e5e5e5",
      background: active ? "#111" : "#fff",
      color:      active ? "#fff" : "#666",
      transition: "all 0.2s ease",
      boxShadow: active ? "0 4px 12px rgba(0,0,0,0.15)" : "none"
    }),
    list:      { display: "flex", flexDirection: "column", gap: 16 },
    item:      (done) => ({
      display: "flex", alignItems: "center", gap: 20, padding: "24px 28px",
      background: "#fff", border: "2px solid #f0f0f0", borderRadius: 20,
      opacity: done ? 0.6 : 1,
      transition: "all 0.2s ease",
      boxShadow: "0 4px 12px rgba(0,0,0,0.03)",
      transform: done ? "scale(0.98)" : "scale(1)",
    }),
    checkBtn:  (done) => ({
      width: 36, height: 36, borderRadius: "50%", flexShrink: 0, cursor: "pointer",
      border: done ? "none" : "2.5px solid #ccc",
      background: done ? "#1D9E75" : "transparent",
      display: "flex", alignItems: "center", justifyContent: "center",
      transition: "all 0.2s ease",
      boxShadow: done ? "0 4px 8px rgba(29, 158, 117, 0.3)" : "none"
    }),
    text:      (done) => ({
      flex: 1, fontSize: 22, lineHeight: 1.5, fontWeight: done ? 400 : 500,
      textDecoration: done ? "line-through" : "none",
      color: done ? "#999" : "#222",
      transition: "all 0.2s ease"
    }),
    tag:       (t) => ({
      padding: "6px 16px", borderRadius: 99, fontSize: 15, fontWeight: 600,
      ...TAG_STYLES[t],
    }),
    delBtn:    { width: 44, height: 44, border: "none", background: "#fee2e2", color: "#ef4444", borderRadius: "50%", cursor: "pointer", fontSize: 20, display: "flex", alignItems: "center", justifyContent: "center", transition: "background 0.2s", fontWeight: "bold" },
    footer:    { marginTop: "3rem", display: "flex", justifyContent: "space-between", alignItems: "center", borderTop: "2px solid #f0f0f0", paddingTop: "2rem" },
    count:     { fontSize: 18, color: "#666", fontWeight: 500 },
    clearBtn:  { fontSize: 16, color: "#ef4444", background: "#fee2e2", border: "none", cursor: "pointer", padding: "12px 24px", borderRadius: 12, fontWeight: 600, transition: "background 0.2s" },
    empty:     { textAlign: "center", padding: "4rem 0", color: "#aaa", fontSize: 22, fontWeight: 500, background: "#fafafa", borderRadius: 20, border: "2px dashed #eaeaea" },
  };

  /* ── render ──────────────────────────────── */
  return (
      <div style={s.app}>
        <h1 style={s.h1}>할 일 목록</h1>
        <p style={s.sub}>{today}</p>

        {/* input */}
        <div style={s.row}>
          <input
              style={s.input}
              value={input}
              placeholder="새로운 할 일 추가..."
              maxLength={100}
              onChange={e => setInput(e.target.value)}
              onKeyDown={e => e.key === "Enter" && addTodo()}
          />
          <select style={s.select} value={tag} onChange={e => setTag(e.target.value)}>
            <option value="">태그 없음</option>
            <option value="personal">개인</option>
            <option value="work">업무</option>
            <option value="health">건강</option>
          </select>
          <button style={s.addBtn} onClick={addTodo}>추가</button>
        </div>

        {/* filters */}
        <div style={s.filters}>
          {["all", "active", "done"].map(f => (
              <button key={f} style={s.filterBtn(filter === f)} onClick={() => setFilter(f)}>
                {{ all: "전체", active: "진행 중", done: "완료" }[f]}
              </button>
          ))}
        </div>

        {/* list */}
        <div style={s.list}>
          {filtered.length === 0 ? (
              <div style={s.empty}>
                {{ done: "완료된 항목이 없어요", active: "모든 할 일을 완료했어요!", all: "할 일을 추가해보세요" }[filter]}
              </div>
          ) : filtered.map(t => (
              <div key={t.id} style={s.item(t.done)}>
                <button style={s.checkBtn(t.done)} onClick={() => toggle(t.id)}>
                  {t.done && (
                      <svg width="20" height="16" viewBox="0 0 10 8" fill="none">
                        <path d="M1 4L3.5 6.5L9 1" stroke="white" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round"/>
                      </svg>
                  )}
                </button>
                <span style={s.text(t.done)}>{t.text}</span>
                {t.tag && <span style={s.tag(t.tag)}>{TAG_LABELS[t.tag]}</span>}
                <button style={s.delBtn} onClick={() => remove(t.id)}>✕</button>
              </div>
          ))}
        </div>

        {/* footer */}
        <div style={s.footer}>
          <span style={s.count}>남은 할 일 {activeCount}개</span>
          <button style={s.clearBtn} onClick={clearDone}>완료 항목 삭제</button>
        </div>
      </div>
  );
}
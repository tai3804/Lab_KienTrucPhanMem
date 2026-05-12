import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../api.js";

const isValidEmail = (value) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);
const isValidPhone = (value) => /^\d{9,11}$/.test(value);

export default function RegisterPage() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ name: "", email: "", phone: "", password: "" });
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (event) => {
    setForm((prev) => ({ ...prev, [event.target.name]: event.target.value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setMessage("");

    const normalizedForm = {
      name: form.name.trim(),
      email: form.email.trim(),
      phone: form.phone.trim(),
      password: form.password.trim()
    };

    if (!normalizedForm.name || !normalizedForm.email || !normalizedForm.phone || !normalizedForm.password) {
      setMessage("Vui long nhap day du thong tin.");
      return;
    }

    if (normalizedForm.name.length < 2) {
      setMessage("Ho ten can it nhat 2 ky tu.");
      return;
    }

    if (!isValidEmail(normalizedForm.email)) {
      setMessage("Email khong dung dinh dang.");
      return;
    }

    if (!isValidPhone(normalizedForm.phone)) {
      setMessage("So dien thoai chi gom 9 den 11 chu so.");
      return;
    }

    if (normalizedForm.password.length < 6) {
      setMessage("Mat khau can it nhat 6 ky tu.");
      return;
    }

    setLoading(true);

    try {
      await api.post("/api/register", normalizedForm);
      navigate("/login");
    } catch (error) {
      setMessage(error.response?.data?.message || "Dang ky that bai.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <section className="auth-section">
      <form className="auth-card" onSubmit={handleSubmit}>
        <h1>Dang ky</h1>
        <input
          name="name"
          placeholder="Ho va ten"
          value={form.name}
          onChange={handleChange}
          autoComplete="name"
        />
        <input
          name="email"
          type="email"
          placeholder="Email"
          value={form.email}
          onChange={handleChange}
          autoComplete="email"
        />
        <input
          name="phone"
          inputMode="numeric"
          placeholder="So dien thoai"
          value={form.phone}
          onChange={handleChange}
          autoComplete="tel"
        />
        <input
          name="password"
          type="password"
          placeholder="Mat khau"
          value={form.password}
          onChange={handleChange}
          autoComplete="new-password"
        />
        <p className="field-hint">Mat khau toi thieu 6 ky tu, so dien thoai gom 9 den 11 chu so.</p>
        <button type="submit" className="primary-button" disabled={loading}>
          {loading ? "Dang xu ly..." : "Tao tai khoan"}
        </button>
        {message && <p className="message error">{message}</p>}
        <p className="switch-text">
          Da co tai khoan? <Link to="/login">Dang nhap</Link>
        </p>
      </form>
    </section>
  );
}

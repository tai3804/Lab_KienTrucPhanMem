import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../api.js";
import { useAuth } from "../context/AuthContext.jsx";

const isValidEmail = (value) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);

export default function LoginPage() {
  const navigate = useNavigate();
  const { login } = useAuth();
  const [form, setForm] = useState({ email: "", password: "" });
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (event) => {
    setForm((prev) => ({ ...prev, [event.target.name]: event.target.value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setMessage("");

    const email = form.email.trim();
    const password = form.password.trim();

    if (!email || !password) {
      setMessage("Vui long nhap day du email va mat khau.");
      return;
    }

    if (!isValidEmail(email)) {
      setMessage("Email khong dung dinh dang.");
      return;
    }

    if (password.length < 6) {
      setMessage("Mat khau can it nhat 6 ky tu.");
      return;
    }

    setLoading(true);

    try {
      const response = await api.post("/api/login", { email, password });
      login(response.data.user);
      navigate("/");
    } catch (error) {
      setMessage(error.response?.data?.message || "Dang nhap that bai.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <section className="auth-section">
      <form className="auth-card" onSubmit={handleSubmit}>
        <h1>Dang nhap</h1>
        <input
          name="email"
          type="email"
          placeholder="Email"
          value={form.email}
          onChange={handleChange}
          autoComplete="email"
        />
        <input
          name="password"
          type="password"
          placeholder="Mat khau"
          value={form.password}
          onChange={handleChange}
          autoComplete="current-password"
        />
        <button type="submit" className="primary-button" disabled={loading}>
          {loading ? "Dang xu ly..." : "Dang nhap"}
        </button>
        {message && <p className="message error">{message}</p>}
        <p className="switch-text">
          Chua co tai khoan? <Link to="/register">Dang ky</Link>
        </p>
      </form>
    </section>
  );
}

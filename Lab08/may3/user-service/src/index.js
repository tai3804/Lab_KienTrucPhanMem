import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import { users } from "./data/users.js";

dotenv.config();

const app = express();
const port = process.env.PORT || 8081;

app.use(cors());
app.use(express.json());

app.get("/health", (_req, res) => {
  res.json({ service: "user-service", status: "ok" });
});

app.post("/register", (req, res) => {
  const { name, email, password, phone } = req.body;

  if (!name || !email || !password) {
    return res.status(400).json({ message: "Thieu thong tin dang ky" });
  }

  const existingUser = users.find((user) => user.email === email);

  if (existingUser) {
    return res.status(409).json({ message: "Email da ton tai" });
  }

  const user = {
    id: `u${users.length + 1}`,
    name,
    email,
    password,
    phone: phone || ""
  };

  users.push(user);

  res.status(201).json({
    message: "Dang ky thanh cong",
    user: {
      id: user.id,
      name: user.name,
      email: user.email,
      phone: user.phone
    }
  });
});

app.post("/login", (req, res) => {
  const { email, password } = req.body;
  const user = users.find((item) => item.email === email && item.password === password);

  if (!user) {
    return res.status(401).json({ message: "Sai email hoac mat khau" });
  }

  res.json({
    message: "Dang nhap thanh cong",
    user: {
      id: user.id,
      name: user.name,
      email: user.email,
      phone: user.phone
    }
  });
});

app.get("/users/:id", (req, res) => {
  const user = users.find((item) => item.id === req.params.id);

  if (!user) {
    return res.status(404).json({ message: "Khong tim thay user" });
  }

  res.json({
    id: user.id,
    name: user.name,
    email: user.email,
    phone: user.phone
  });
});

app.listen(port, () => {
  console.log(`User Service listening on port ${port}`);
});

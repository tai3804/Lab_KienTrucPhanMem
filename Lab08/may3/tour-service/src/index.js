import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import { tours } from "./data/tours.js";

dotenv.config();

const app = express();
const port = process.env.PORT || 8082;

app.use(cors());

app.get("/health", (_req, res) => {
  res.json({ service: "tour-service", status: "ok" });
});

app.get("/tours", (_req, res) => {
  res.json(tours);
});

app.get("/tours/:id", (req, res) => {
  const tour = tours.find((item) => item.id === req.params.id);

  if (!tour) {
    return res.status(404).json({ message: "Khong tim thay tour" });
  }

  res.json(tour);
});

app.listen(port, () => {
  console.log(`Tour Service listening on port ${port}`);
});

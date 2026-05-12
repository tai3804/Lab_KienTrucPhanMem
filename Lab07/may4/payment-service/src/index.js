import express from "express";
import cors from "cors";
import dotenv from "dotenv";

dotenv.config();

const app = express();
const port = process.env.PORT || 8084;
const successRate = Number(process.env.SUCCESS_RATE || 0.7);

app.use(cors());
app.use(express.json());

app.get("/health", (_req, res) => {
  res.json({ service: "payment-service", status: "ok" });
});

app.post("/payments", (req, res) => {
  const { bookingId, amount, paymentMethod } = req.body;

  if (!bookingId || !amount) {
    return res.status(400).json({ message: "Thieu thong tin thanh toan" });
  }

  const isSuccess = Math.random() < successRate;

  res.json({
    paymentId: `p${Date.now()}`,
    bookingId,
    amount,
    paymentMethod: paymentMethod || "cash",
    status: isSuccess ? "SUCCESS" : "FAILED",
    processedAt: new Date().toISOString()
  });
});

app.listen(port, () => {
  console.log(`Payment Service listening on port ${port}`);
});

import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import { bookings } from "./data/bookings.js";

dotenv.config();

const app = express();
const port = process.env.PORT || 8083;

app.use(cors());
app.use(express.json());

app.get("/health", (_req, res) => {
  res.json({ service: "booking-service", status: "ok" });
});

app.post("/bookings", (req, res) => {
  const { userId, userName, tourId, tourName, price, paymentMethod } = req.body;

  if (!userId || !tourId) {
    return res.status(400).json({ message: "Thieu thong tin booking" });
  }

  const booking = {
    id: `b${bookings.length + 1}`,
    userId,
    userName,
    tourId,
    tourName,
    price,
    paymentMethod,
    status: "PENDING",
    createdAt: new Date().toISOString()
  };

  bookings.push(booking);
  res.status(201).json(booking);
});

app.patch("/bookings/:id/status", (req, res) => {
  const booking = bookings.find((item) => item.id === req.params.id);

  if (!booking) {
    return res.status(404).json({ message: "Khong tim thay booking" });
  }

  booking.status = req.body.status || booking.status;
  booking.paymentId = req.body.paymentId || booking.paymentId;
  booking.paymentStatus = req.body.paymentStatus || booking.paymentStatus;

  res.json(booking);
});

app.get("/bookings/user/:userId", (req, res) => {
  const userBookings = bookings.filter((item) => item.userId === req.params.userId);
  res.json(userBookings);
});

app.listen(port, () => {
  console.log(`Booking Service listening on port ${port}`);
});

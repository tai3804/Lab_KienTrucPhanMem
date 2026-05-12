import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import axios from "axios";

dotenv.config();

const app = express();
const port = process.env.PORT || 8080;

app.use(cors());
app.use(express.json());

const api = axios.create({
  timeout: 5000,
  proxy: false
});

const services = {
  user: process.env.USER_SERVICE_URL,
  tour: process.env.TOUR_SERVICE_URL,
  booking: process.env.BOOKING_SERVICE_URL,
  payment: process.env.PAYMENT_SERVICE_URL
};

const buildError = (error, fallbackMessage) => {
  const status = error.response?.status || 500;
  const message = error.response?.data?.message || fallbackMessage;
  return { status, message };
};

app.get("/health", (_req, res) => {
  res.json({ service: "orchestrator-service", status: "ok" });
});

app.post("/api/register", async (req, res) => {
  try {
    const response = await api.post(`${services.user}/register`, req.body);
    res.status(201).json(response.data);
  } catch (error) {
    const { status, message } = buildError(error, "Khong the dang ky");
    res.status(status).json({ message });
  }
});

app.post("/api/login", async (req, res) => {
  try {
    const response = await api.post(`${services.user}/login`, req.body);
    res.json(response.data);
  } catch (error) {
    const { status, message } = buildError(error, "Dang nhap that bai");
    res.status(status).json({ message });
  }
});

app.get("/api/profile/:id", async (req, res) => {
  try {
    const [userResponse, bookingsResponse] = await Promise.all([
      api.get(`${services.user}/users/${req.params.id}`),
      api.get(`${services.booking}/bookings/user/${req.params.id}`)
    ]);

    res.json({
      user: userResponse.data,
      bookings: bookingsResponse.data
    });
  } catch (error) {
    const { status, message } = buildError(error, "Khong tai duoc thong tin ca nhan");
    res.status(status).json({ message });
  }
});

app.get("/api/tours", async (_req, res) => {
  try {
    const response = await api.get(`${services.tour}/tours`);
    res.json(response.data);
  } catch (error) {
    const { status, message } = buildError(error, "Khong lay duoc danh sach tour");
    res.status(status).json({ message });
  }
});

app.get("/api/tours/:id", async (req, res) => {
  try {
    const response = await api.get(`${services.tour}/tours/${req.params.id}`);
    res.json(response.data);
  } catch (error) {
    const { status, message } = buildError(error, "Khong lay duoc chi tiet tour");
    res.status(status).json({ message });
  }
});

app.get("/api/my-bookings/:userId", async (req, res) => {
  try {
    const response = await api.get(`${services.booking}/bookings/user/${req.params.userId}`);
    res.json(response.data);
  } catch (error) {
    const { status, message } = buildError(error, "Khong lay duoc lich su booking");
    res.status(status).json({ message });
  }
});

app.post("/api/book-tour", async (req, res) => {
  const { userId, tourId, paymentMethod = "cash" } = req.body;

  if (!userId || !tourId) {
    return res.status(400).json({ message: "Thieu userId hoac tourId" });
  }

  try {
    const userResponse = await api.get(`${services.user}/users/${userId}`);
    const tourResponse = await api.get(`${services.tour}/tours/${tourId}`);

    const bookingPayload = {
      userId,
      userName: userResponse.data.name,
      tourId,
      tourName: tourResponse.data.name,
      price: tourResponse.data.price,
      paymentMethod
    };

    const bookingResponse = await api.post(`${services.booking}/bookings`, bookingPayload);

    const paymentResponse = await api.post(`${services.payment}/payments`, {
      bookingId: bookingResponse.data.id,
      amount: tourResponse.data.price,
      paymentMethod
    });

    const finalStatus = paymentResponse.data.status === "SUCCESS" ? "CONFIRMED" : "PAYMENT_FAILED";

    const updatedBookingResponse = await api.patch(
      `${services.booking}/bookings/${bookingResponse.data.id}/status`,
      {
        status: finalStatus,
        paymentId: paymentResponse.data.paymentId,
        paymentStatus: paymentResponse.data.status
      }
    );

    const confirmationMessage =
      finalStatus === "CONFIRMED"
        ? `Dat tour thanh cong cho ${tourResponse.data.name}`
        : `Thanh toan that bai cho booking ${bookingResponse.data.id}`;

    res.status(finalStatus === "CONFIRMED" ? 201 : 202).json({
      message: confirmationMessage,
      user: userResponse.data,
      tour: tourResponse.data,
      booking: updatedBookingResponse.data,
      payment: paymentResponse.data,
      notification: {
        sent: finalStatus === "CONFIRMED",
        channel: "system",
        content:
          finalStatus === "CONFIRMED"
            ? `Xin chuc mung ${userResponse.data.name}, booking cua ban da duoc xac nhan.`
            : "Booking da duoc tao nhung thanh toan chua thanh cong."
      }
    });
  } catch (error) {
    const { status, message } = buildError(error, "Khong the hoan tat quy trinh dat tour");
    res.status(status).json({ message });
  }
});

app.listen(port, () => {
  console.log(`Orchestrator Service listening on port ${port}`);
});

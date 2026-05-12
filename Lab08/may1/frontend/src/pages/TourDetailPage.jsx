import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../api.js";
import { useAuth } from "../context/AuthContext.jsx";

export default function TourDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { user } = useAuth();
  const [tour, setTour] = useState(null);
  const [message, setMessage] = useState("");
  const [bookingResult, setBookingResult] = useState(null);
  const [loading, setLoading] = useState(true);
  const [bookingLoading, setBookingLoading] = useState(false);

  useEffect(() => {
    const loadTour = async () => {
      try {
        const response = await api.get(`/api/tours/${id}`);
        setTour(response.data);
      } catch (error) {
        setMessage(error.response?.data?.message || "Không tải được chi tiết tour.");
      } finally {
        setLoading(false);
      }
    };

    loadTour();
  }, [id]);

  const handleBookTour = async () => {
    if (!user) {
      navigate("/login");
      return;
    }

    setBookingLoading(true);
    setMessage("");
    setBookingResult(null);

    try {
      const response = await api.post("/api/book-tour", {
        userId: user.id,
        tourId: id,
        paymentMethod: "banking"
      });
      setBookingResult(response.data);
    } catch (error) {
      setMessage(error.response?.data?.message || "Đặt tour thất bại.");
    } finally {
      setBookingLoading(false);
    }
  };

  if (loading) {
    return <section className="page-section">Đang tải chi tiết tour...</section>;
  }

  if (!tour) {
    return <section className="page-section">{message || "Không tìm thấy tour."}</section>;
  }

  return (
    <section className="page-section detail-layout">
      <img src={tour.image} alt={tour.name} className="detail-image" />
      <div className="detail-card">
        <p className="eyebrow">{tour.location}</p>
        <h1>{tour.name}</h1>
        <p>{tour.description}</p>
        <div className="detail-meta">
          <span>{tour.duration}</span>
          <strong>{tour.price.toLocaleString("vi-VN")} VND</strong>
        </div>
        <button type="button" className="primary-button" onClick={handleBookTour} disabled={bookingLoading}>
          {bookingLoading ? "Đang đặt tour..." : "Đặt tour"}
        </button>
        {message && <p className="message error">{message}</p>}
        {bookingResult && (
          <div className={`message ${bookingResult.booking.status === "CONFIRMED" ? "success" : "warning"}`}>
            <p>{bookingResult.message}</p>
            <p>Mã booking: {bookingResult.booking.id}</p>
            <p>Thanh toán: {bookingResult.payment.status}</p>
          </div>
        )}
      </div>
    </section>
  );
}

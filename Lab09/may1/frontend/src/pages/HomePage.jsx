import { useEffect, useState } from "react";
import api from "../api.js";
import TourCard from "../components/TourCard.jsx";

export default function HomePage() {
  const [tours, setTours] = useState([]);
  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState("");

  useEffect(() => {
    const loadTours = async () => {
      try {
        const response = await api.get("/api/tours");
        setTours(response.data);
      } catch (error) {
        setMessage(error.response?.data?.message || "Không tải được danh sách tour.");
      } finally {
        setLoading(false);
      }
    };

    loadTours();
  }, []);

  return (
    <section className="page-section">
      <div className="hero">
        <div>
          <p className="eyebrow">Orchestration-Driven SOA</p>
          <h1>Đặt tour nhanh gọn, frontend chỉ gọi Orchestrator.</h1>
          <p>Demo luồng User, Tour, Booking và Payment Service thông qua một đầu mối điều phối.</p>
        </div>
      </div>

      {loading && <p>Đang tải tour...</p>}
      {message && <p className="message error">{message}</p>}

      <div className="tour-grid">
        {tours.map((tour) => (
          <TourCard key={tour.id} tour={tour} />
        ))}
      </div>
    </section>
  );
}

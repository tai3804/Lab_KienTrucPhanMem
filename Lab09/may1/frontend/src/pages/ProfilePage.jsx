import { useEffect, useState } from "react";
import api from "../api.js";
import { useAuth } from "../context/AuthContext.jsx";

export default function ProfilePage() {
  const { user } = useAuth();
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState("");

  useEffect(() => {
    const loadProfile = async () => {
      try {
        const response = await api.get(`/api/profile/${user.id}`);
        setProfile(response.data);
      } catch (error) {
        setMessage(error.response?.data?.message || "Không tải được thông tin cá nhân.");
      } finally {
        setLoading(false);
      }
    };

    loadProfile();
  }, [user.id]);

  if (loading) {
    return <section className="page-section">Đang tải thông tin cá nhân...</section>;
  }

  if (!profile) {
    return <section className="page-section">{message}</section>;
  }

  return (
    <section className="page-section profile-layout">
      <div className="profile-card">
        <p className="eyebrow">Thông tin cá nhân</p>
        <h1>{profile.user.name}</h1>
        <p>Email: {profile.user.email}</p>
        <p>Số điện thoại: {profile.user.phone || "Chưa cập nhật"}</p>
      </div>

      <div className="profile-card">
        <p className="eyebrow">Lịch sử booking</p>
        {profile.bookings.length === 0 ? (
          <p>Chưa có booking nào.</p>
        ) : (
          <div className="booking-list">
            {profile.bookings.map((booking) => (
              <div key={booking.id} className="booking-item">
                <strong>{booking.tourName}</strong>
                <p>Mã booking: {booking.id}</p>
                <p>Trạng thái: {booking.status}</p>
                <p>Giá: {booking.price.toLocaleString("vi-VN")} VND</p>
              </div>
            ))}
          </div>
        )}
      </div>
    </section>
  );
}

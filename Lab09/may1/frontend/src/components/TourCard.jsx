import { Link } from "react-router-dom";

export default function TourCard({ tour }) {
  return (
    <article className="tour-card">
      <img src={tour.image} alt={tour.name} className="tour-image" />
      <div className="tour-card-body">
        <p className="tour-location">{tour.location}</p>
        <h3>{tour.name}</h3>
        <p>{tour.shortDescription}</p>
        <div className="tour-meta">
          <span>{tour.duration}</span>
          <strong>{tour.price.toLocaleString("vi-VN")} VND</strong>
        </div>
        <Link to={`/tours/${tour.id}`} className="primary-button">
          Xem chi tiết
        </Link>
      </div>
    </article>
  );
}

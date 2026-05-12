# Travel Booking System - Orchestration-Driven SOA

He thong duoc chia thanh 4 may de de trien khai trong LAN:

1. `may1/frontend`
2. `may2/orchestrator-service`
3. `may3/user-service` va `may3/tour-service`
4. `may4/booking-service` va `may4/payment-service`

## Kien truc

- Frontend chi goi `orchestrator-service`.
- Orchestrator goi lan luot `user-service`, `tour-service`, `booking-service`, `payment-service`.
- Cac service khong goi truc tiep nhau.
- Tat ca host/IP tach trong file `.env`.

## API chinh

- Orchestrator:
  - `POST /api/register`
  - `POST /api/login`
  - `GET /api/profile/:id`
  - `GET /api/tours`
  - `GET /api/tours/:id`
  - `GET /api/my-bookings/:userId`
  - `POST /api/book-tour`
- User Service:
  - `POST /register`
  - `POST /login`
  - `GET /users/:id`
- Tour Service:
  - `GET /tours`
  - `GET /tours/:id`
- Booking Service:
  - `POST /bookings`
  - `PATCH /bookings/:id/status`
  - `GET /bookings/user/:userId`
- Payment Service:
  - `POST /payments`

## Chay tung phan

Tai moi folder service/frontend:

1. Copy `.env.example` thanh `.env`
2. Sua lai IP/host dung voi LAN cua ban
3. Cai goi:

```bash
npm install
```

4. Chay:

```bash
npm run dev
```

## Thu tu khoi dong

1. `may3/user-service`
2. `may3/tour-service`
3. `may4/booking-service`
4. `may4/payment-service`
5. `may2/orchestrator-service`
6. `may1/frontend`

## IP hien tai

- Orchestrator: `192.168.137.178:8080`
- User: `192.168.137.178:8081`
- Tour: `192.168.137.178:8082`
- Booking: `192.168.137.178:8083`
- Payment: `192.168.137.178:8084`
- Frontend: `192.168.137.178:3000`

## Chay nhanh tren cung 1 may

- Chay tat ca: `powershell -ExecutionPolicy Bypass -File .\\start-all.ps1`
- Dung tat ca: `powershell -ExecutionPolicy Bypass -File .\\stop-all.ps1`

## Trang frontend

- Dang ky
- Dang nhap
- Trang chu xem danh sach tour
- Trang chi tiet tour va dat tour
- Trang ca nhan va lich su booking

## Ghi chu

- `payment-service` tra ket qua thanh cong/that bai ngau nhien theo `SUCCESS_RATE`.
- Du lieu hien tai duoc luu trong bo nho de de demo tren LAN.

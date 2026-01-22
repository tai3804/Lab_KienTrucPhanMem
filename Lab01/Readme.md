## Lab1 - Rabbit queue
### Trần Thành Tài - 22673121

### 1. Giới thiệu
Trong project hiện tại có 4 proejct nhỏ bên trong lần lượt là
- consumer
- producer
- consumer_dlq
- producer_dlq

Hiện tại, project đang được cấu hình mặt định để chạy consumer và producer (phiên bản không có dead letter)  

Để thay đổi thành bản consumer_dlq và producer_dlq (phiên bản có dead letter):    
Vào file **docker-compose.yml** ở thư mục root,
đổi build ở producer thành producer_dlq và build ở consumer thành consumer_dlq giống đoạn code bên dưới hoặc copy cả đoạn và 
dán vào **docker-compose.yml**  
Để đổi được phiên bản rabbit phải vào docker desktop để xoá phiễn bản cũ vì khác cấu hình gây xung đột
```angular2html
services:
  rabbitmq:
    image: rabbitmq:3.13-management
    container_name: rabbitmq
    environment:
      RABBITMQ_DEFAULT_USER: root
      RABBITMQ_DEFAULT_PASS: root
    ports:
      - "5672:5672"
      - "15672:15672"
  producer:
    build: ./producer_dlq
    container_name: producer
    ports:
      - "8080:8080"
    environment:
      SPRING_RABBITMQ_HOST: rabbitmq
      SPRING_RABBITMQ_USERNAME: root
      SPRING_RABBITMQ_PASSWORD: root
    depends_on:
      - rabbitmq
  consumer:
    build: ./consumer_dlq
    container_name: consumer
    environment:
      SPRING_RABBITMQ_HOST: rabbitmq
      SPRING_RABBITMQ_USERNAME: root
      SPRING_RABBITMQ_PASSWORD: root
    depends_on:
      - rabbitmq
```
### 2. Chạy project
Để khỏi chạy project, thực hiện theo các bước sao:
1. build project
```
    docker compose build
```
2. Chạy project
```
    docker compose up
```

Hoặc
```
    docker compose up --build
```

### 3. Hướng dẫn sử dụng
- Với bản không sử dụng dead letter:  
curl -X POST http://localhost:3000/send \
    -H "Content-Type: application/json" \
    -d '{"message":"Order #1000 send"}'

- Với bản dùng dead letter:  
  curl -X POST http://localhost:3000/send \
  -H "Content-Type: application/json" \
  -d '{"orderId":"Order #1000 send", "message":"Test"}'
- Với bản có dead letter để gửi được tin nhắn gây lỗi chỉ cần bỏ orderId hoặc message hoặc gửi message có chữ "fail"
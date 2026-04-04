FROM python:3.9

# Thiết lập biến môi trường
ENV APP_ENV="Hello world"

WORKDIR /app
COPY . .

# Chạy script Python (đoạn code python cần dùng os.environ.get('APP_ENV'))
CMD ["python", "main.py"]
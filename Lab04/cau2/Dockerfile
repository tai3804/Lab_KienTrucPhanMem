FROM python:3.9

WORKDIR /app

# Cài đặt Flask
RUN pip install flask

COPY . .

# Mở cổng 5000
EXPOSE 5000

CMD ["python", "index.py"]
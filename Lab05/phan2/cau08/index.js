const express = require('express');
const mysql = require('mysql2');
const app = express();

// QUAN TRỌNG: host là 'db' (tên service trong docker-compose)
const db = mysql.createConnection({
  host: 'db',
  user: 'root',
  password: 'rootpassword',
  database: 'testdb'
});

db.connect((err) => {
  if (err) {
    console.error('Không thể kết nối MySQL:', err.message);
    return;
  }
  console.log('Đã kết nối MySQL thành công!');
});

app.get('/', (req, res) => {
  res.send('Node.js và MySQL đã thông tuyến! 🚀');
});

app.listen(3000, () => console.log('App chạy ở cổng 3000'));
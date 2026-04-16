const express = require("express");
const { Pool } = require("pg");

const app = express();
const port = 5001;

const pool = new Pool({
  host: process.env.PGHOST || "db",
  user: process.env.PGUSER || "postgres",
  password: process.env.PGPASSWORD || "postgres",
  database: process.env.PGDATABASE || "votesdb",
  port: Number(process.env.PGPORT || 5432)
});

app.get("/", async (_req, res) => {
  await pool.query(
    "CREATE TABLE IF NOT EXISTS votes (choice TEXT PRIMARY KEY, total INTEGER NOT NULL)"
  );

  const { rows } = await pool.query(
    "SELECT choice, total FROM votes ORDER BY choice ASC"
  );

  const map = rows.reduce((acc, row) => {
    acc[row.choice] = row.total;
    return acc;
  }, {});

  res.send(`
    <html>
      <body style="font-family: Arial; max-width: 520px; margin: 40px auto;">
        <h1>Voting Result</h1>
        <p>Cats: ${map.cats || 0}</p>
        <p>Dogs: ${map.dogs || 0}</p>
        <p><a href="http://localhost:5000">Back to vote</a></p>
      </body>
    </html>
  `);
});

app.listen(port, () => {
  console.log(`Result service listening on ${port}`);
});

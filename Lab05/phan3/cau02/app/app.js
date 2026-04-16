const express = require("express");
const { MongoClient } = require("mongodb");

const app = express();
app.use(express.json());

const port = process.env.PORT || 3000;
const mongoUrl = process.env.MONGO_URL || "mongodb://mongo:27017/labdb";

let collection;

async function connectWithRetry() {
  const client = new MongoClient(mongoUrl);
  while (!collection) {
    try {
      await client.connect();
      collection = client.db().collection("messages");
      console.log("Connected to MongoDB");
    } catch (error) {
      console.log("MongoDB not ready, retrying in 5 seconds");
      await new Promise((resolve) => setTimeout(resolve, 5000));
    }
  }
}

connectWithRetry();

app.get("/", async (_req, res) => {
  const items = collection ? await collection.find().toArray() : [];
  res.json({
    message: "Node.js + MongoDB is running",
    count: items.length,
    items
  });
});

app.post("/messages", async (req, res) => {
  if (!collection) {
    return res.status(503).json({ error: "Database not ready" });
  }

  const text = req.body.text || "Hello from Docker Compose";
  const result = await collection.insertOne({
    text,
    createdAt: new Date()
  });

  res.status(201).json({ insertedId: result.insertedId, text });
});

app.listen(port, () => {
  console.log(`Server listening on port ${port}`);
});

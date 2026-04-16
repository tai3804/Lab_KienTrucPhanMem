from flask import Flask
import os

app = Flask(__name__)


@app.route("/")
def home():
    return {
        "message": "Hello from Flask behind Nginx",
        "instance": os.getenv("INSTANCE_NAME", "unknown"),
    }


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)

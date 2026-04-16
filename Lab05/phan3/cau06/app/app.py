from flask import Flask
import os

app = Flask(__name__)


@app.get("/")
def home():
    return {
        "message": "CI/CD demo app",
        "environment": os.getenv("APP_ENV", "development"),
    }


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8000)

import os
from flask import Flask, redirect, render_template_string, request, url_for
import redis

app = Flask(__name__)
cache = redis.Redis(host=os.getenv("REDIS_HOST", "redis"), port=6379, db=0)

PAGE = """
<!doctype html>
<html>
  <head><title>Voting App</title></head>
  <body style="font-family: Arial; max-width: 520px; margin: 40px auto;">
    <h1>Vote for your favorite pet</h1>
    <form method="post" action="/vote">
      <button type="submit" name="choice" value="cats">Vote Cats</button>
      <button type="submit" name="choice" value="dogs">Vote Dogs</button>
    </form>
    <p><a href="http://localhost:5001">View result service</a></p>
  </body>
</html>
"""


@app.get("/")
def index():
    return render_template_string(PAGE)


@app.post("/vote")
def vote():
    choice = request.form.get("choice", "cats")
    cache.rpush("votes", choice)
    return redirect(url_for("index"))


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)

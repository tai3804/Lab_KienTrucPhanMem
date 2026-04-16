#!/bin/sh
set -eu

pip install --no-cache-dir -r requirements.txt
last_sum=""

while true
do
  current_sum="$(find . -type f \( -name '*.py' -o -name 'requirements.txt' \) | sort | xargs cat | sha256sum | awk '{print $1}')"
  if [ "$current_sum" != "$last_sum" ]; then
    echo "Change detected. Running tests..."
    pytest -q
    last_sum="$current_sum"
  fi
  sleep 3
done

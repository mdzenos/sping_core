#!/bin/bash

APP_PID=0

start() {
  mvn spring-boot:run -Dspring-boot.run.fork=false -Dserver.port=8000 &
  APP_PID=$!
}

stop() {
  if [ $APP_PID -ne 0 ]; then
    kill $APP_PID
    wait $APP_PID 2>/dev/null
  fi
}

start

while true; do
  inotifywait -r -e modify,create,delete src/
  echo "Reloading..."
  stop
  start
done

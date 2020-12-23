# Spring Boot REST Api using PostgreSQL and Docker Compose

## BUILD the app
./gradlew build   

## BUILD and up the containers Docker Compose 
docker-compose up --build      

## Endpoints:
http://localhost:8080/send_message
http://localhost:8080/send_emotion

### CURLS
curl -s -X POST \
  http://localhost:8080/messages/send_text \
  -H 'Content-Type: application/json' \
  -d '{"payload":"text"}'

curl -s -X POST \
  http://localhost:8080/messages/send_emotion \
  -H 'Content-Type: application/json' \
  -d '{"payload":"a_b_#%^"}'

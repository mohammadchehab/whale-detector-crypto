https://stackoverflow.com/questions/27988888/docker-linking-spring-boot-container-with-mongo-db-container

docker run -d -P --name=my-mongo --network=host mongo:latest
Then in your Spring boot app, you will have something like this:-

spring:
  application:
    name: my-app
  data:
    mongodb:
      database: app
      host: 192.168.99.100 // your machine private ip.
      port: 27017
Run you spring boot app from image using :-

docker run -d -P --name=my-boot-app --network=host my-app-image

#delete all exited containers
sudo docker ps -a | grep Exit | cut -d ' ' -f 1 | xargs sudo docker rm
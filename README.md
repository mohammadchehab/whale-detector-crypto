# whale-detector-crypto
Detecting dumps and pumps via a java sprint boot app with mongo db

docker run -d -P --name=my-mongo --network=host mongo:latest Then in your Spring boot app, you will have something like this:-

spring: application: name: my-app data: mongodb: database: app host: 192.168.99.100 // your machine private ip. port: 27017 Run you spring boot app from image using :-

docker run -d -P --name=my-boot-app --network=host my-app-image


FROM openjdk:8
EXPOSE 8080
ADD target/orderbackend-1.jar orderbackend-1.jar
ENTRYPOINT ["java","-jar","/orderbackend-1.jar"]

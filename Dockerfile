FROM openjdk:8
EXPOSE 8080
ADD target/orderbackend.jar orderbackend.jar
ENTRYPOINT ["java","-jar","/orderbackend.jar"]

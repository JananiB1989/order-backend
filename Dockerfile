FROM openjdk:15
EXPOSE 8080
ADD target/order-backend-1.jar order-backend-1.jar
ENTRYPOINT ["java","-jar","/order-backend-1.jar"]

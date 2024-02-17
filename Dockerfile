FROM openjdk:17-alpine
EXPOSE 8085
ADD target/ecommerce-1.0.0.jar ecommerce-1.0.0.jar
ENTRYPOINT ["java","-jar","/ecommerce-1.0.0.jar"]
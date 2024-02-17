FROM openjdk:17-alpine
EXPOSE 8085
ADD target/ecommnerce-1.0.0.jar ecommnerce-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/ecommerce-1.0.0.jar"]
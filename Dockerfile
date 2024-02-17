FROM openjdk:17-alpine
EXPOSE 8085
ADD target/ecommnerce-1.0.0.jar commnerce-1.0.0.jar
ENTRYPOINT ["java" ,"-jar", "/commnerce-1.0.0.jar"]
FROM openjdk:17-alpine
EXPOSE 8085
ADD target/ecommnerce-1.0.0.jar commnerce-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/commerce-1.0.0.jar", \
                       "--db.host=host.docker.internal", \
                       "--db.port=5432", \
                       "--db.name=your_database_name", \
                       "--db.username=your_database_username", \
                       "--db.password=your_database_password"]
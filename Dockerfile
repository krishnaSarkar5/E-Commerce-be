FROM openjdk:17-alpine
EXPOSE 8085
ADD target/ecommnerce-1.0.0.jar ecommnerce-1.0.0.jar
ENTRYPOINT ["java","-jar","/ecommnerce-1.0.0.jar"\
             "--db.host=host.docker.internal", \
             "--db.port=5432", \
             "--db.name=ecommnerce", \
             "--db.username=postgres", \
             "--db.password=postgres"]
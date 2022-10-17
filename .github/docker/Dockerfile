FROM amazoncorretto:11-alpine
WORKDIR /home/app/homepage-backend
COPY ./target/*.jar ./homepage-backend.jar
EXPOSE 8080
CMD ["java", "-jar", "homepage-backend.jar"]

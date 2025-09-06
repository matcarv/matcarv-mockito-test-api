FROM eclipse-temurin:17-jdk
WORKDIR /home/app
ENV TZ=GMT-3
COPY target/*.jar /home/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]
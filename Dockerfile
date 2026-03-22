FROM openjdk:17

WORKDIR /app

COPY backend /app

RUN chmod +x mvnw
RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/*.jar"]
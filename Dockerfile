FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY backend /app

RUN chmod +x mvnw
RUN ./mvnw clean package

EXPOSE 8080

# fix CMD
CMD ["java", "-jar", "target/library-management-system-0.0.1-SNAPSHOT.jar"]
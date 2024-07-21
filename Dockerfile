# Prva faza: build
FROM openjdk:17-jdk-slim as builder

# Postavljamo radni direktorijum
WORKDIR /app

# Kopiramo Gradle wrapper i build fajlove
COPY gradlew .
COPY gradle /app/gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Kopiramo source kod
COPY src /app/src

# Postavljamo prava za izvršavanje gradlew skripte
RUN chmod +x ./gradlew

# Koristimo cache mount za Gradle da ubrzamo build
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build

# Druga faza: runtime
FROM openjdk:17-jdk-slim

# Definišemo argument za JAR fajl
ARG JAR_FILE=build/libs/door2door-backend.jar

# Kopiramo JAR fajl iz prethodne faze builda
COPY --from=builder /app/${JAR_FILE} app.jar

# Otvaramo port 8080
EXPOSE 8080

# Definišemo ENTRYPOINT
ENTRYPOINT ["java", "-jar", "/app.jar"]
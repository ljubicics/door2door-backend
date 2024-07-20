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

FROM openjdk:17-jdk-slim

ARG JAR_FILE=build/libs/door2door-backend.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
# 1. Usa um computador virtual com Maven e Java 17 para compilar o código
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY src/main/java/com/matias/financas_api .
RUN mvn clean package -DskipTests

# 2. Pega apenas no ficheiro compilado e coloca num contentor leve para rodar
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY . .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
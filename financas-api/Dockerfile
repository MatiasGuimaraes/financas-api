# 1. Usa um computador virtual com Maven e Java 21 para compilar o codigo
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
# Apaga arquivos secundários para evitar que o Docker crie uma pasta acidentalmente
RUN rm -f target/*-plain.jar target/original-*.jar

# 2. Pega apenas no ficheiro compilado e coloca num contentor leve para rodar
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
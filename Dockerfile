FROM maven:3.9.9-eclipse-temurin-21 as maven_build
LABEL authors="Evaldo"

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=maven_build /app/target/desafio-ibm-0.0.1-SNAPSHOT.jar ./desafio-ibm-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "desafio-ibm-0.0.1-SNAPSHOT.jar"]
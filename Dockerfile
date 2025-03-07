FROM eclipse-temurin:21

LABEL authors="kaushalkishore"
LABEL version="1.0"
LABEL description="Todo Spring Boot Demo"
LABEL name="Todo_SpringBoot_Demo_V1.jar"

WORKDIR /app

COPY target/Todo_SpringBoot_Demo_V1.jar /app/Todo_SpringBoot_Demo_V1.jar

ENTRYPOINT ["java","-jar","Todo_SpringBoot_Demo_V1.jar"]

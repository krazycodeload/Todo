FROM eclipse-temurin:21

LABEL authors="kaushalkishore"
LABEL version="1.0"
LABEL description="Todo Spring Boot Demo"
LABEL name="Todo_SpringBoot_Demo_V1.jar"

WORKDIR /app

COPY target/Todo_SpringBoot_Demo_V1.jar /app/Todo_SpringBoot_Demo_V1.jar

ENTRYPOINT ["java","-Dlogging.debug=true","-jar","Todo_SpringBoot_Demo_V1.jar"]

# Set the SPRING_PROFILES_ACTIVE environment variable
ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE
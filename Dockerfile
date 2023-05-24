FROM eclipse-temurin:17-jdk

ENV GMAPS_STATIC_API_KEY $GMAPS_STATIC_API_KEY

ENV CUSTOM_MARKER_SOURCE $CUSTOM_MARKER_SOURCE

RUN adduser --system --group spring

USER spring:spring

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
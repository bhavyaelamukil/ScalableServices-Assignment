FROM openjdk:17.0.2-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} email-service-app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /email-service-app.jar"]

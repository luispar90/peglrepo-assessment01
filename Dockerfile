FROM eclipse-temurin:17-jdk

# Set directory path
WORKDIR /app

# Copy JAR file to the docker directory path
COPY ./target/peglsrv-assessment01.jar /app

# Set port number
EXPOSE 8080

# Exceute the app
ENTRYPOINT ["java", "-jar", "peglsrv-assessment01.jar"]

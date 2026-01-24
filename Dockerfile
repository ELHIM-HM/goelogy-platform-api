# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./srce
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Install Python and dependencies
# We create a virtual environment and add it to PATH so 'python' and 'pip' work globally
RUN apk add --no-cache python3 py3-pip && \
    python3 -m venv /opt/venv && \
    /opt/venv/bin/pip install requests faker

# Add venv to PATH so 'python' command works for DataSeeder
ENV PATH="/opt/venv/bin:$PATH"

# Copy the built JAR file
COPY --from=build /app/target/*.jar app.jar

# Copy the python script
COPY populate_db.py .

# Expose the port
EXPOSE 9000

# Set the entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]

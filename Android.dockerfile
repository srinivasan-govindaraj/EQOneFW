FROM budtmo/docker-android:latest

USER root

# Install Maven
RUN apt-get update && DEBIAN_FRONTEND=noninteractive apt-get install -y maven

# Copy your project files
COPY pom.xml /app/
COPY src /app/src/

# Set working directory
WORKDIR /app

# Expose necessary ports
EXPOSE 6080 4723 5554 5555

# Set environment variables
ENV DEVICE="Samsung Galaxy S10" \
    APPIUM=true \
    CONNECT_TO_GRID=true \
    APPIUM_HOST="127.0.0.1" \
    APPIUM_PORT=4723

# Command to run your tests
CMD ["mvn", "test"]

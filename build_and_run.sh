#!/bin/bash

# Extract the version from pom.xml
VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# Define the JAR file name based on the version
JAR_FILE="HouseManagement-${VERSION}.jar"

# Build the Docker image with the JAR_FILE argument
docker-compose -f docker-compose-house.yml build --build-arg JAR_FILE=${JAR_FILE}

# Run the Docker Compose services
DATABASE=house_app USERNAME=postgres PASSWORD=postgres docker-compose -f docker-compose-house.yml up

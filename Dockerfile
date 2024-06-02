#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Run stage
#
FROM openjdk:17
COPY --from=build /home/app/target/HouseManagement-0.0.2-SNAPSHOT.jar /usr/local/lib/HouseManagement-0.0.2-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/HouseManagement-0.0.2-SNAPSHOT.jar"]
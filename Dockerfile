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
ARG JAR_FILE

#finish itFinish docker file
COPY --from=build /home/app/target/${JAR_FILE} /usr/local/lib/${JAR_FILE}
#COPY --from=build /home/app/target/HouseManagement-1.0.0-SNAPSHOT.jar /usr/local/lib/HouseManagement-1.0.0-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/usr/local/lib/${JAR_FILE}"]
#ENTRYPOINT ["java","-jar","/usr/local/lib/HouseManagement-1.0.0-SNAPSHOT.jar"]
FROM maven:3.8.7 as build
WORKDIR /appsafimovil
COPY pom.xml /appsafimovil
RUN mvn dependency:resolve
COPY . /appsafimovil
RUN mvn clean
RUN mvn package -DskipTests -X

FROM openjdk:17
COPY --from=build /appsafimovil/target/*.war /appsafimovil.war
EXPOSE 8080
CMD ["java","-Dspring.profiles.active=prod", "-jar", "/appsafimovil.jar"]
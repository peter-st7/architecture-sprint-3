FROM gradle:8-jdk17-alpine as build
WORKDIR /build
COPY . .
RUN gradle clean build

FROM azul/zulu-openjdk-alpine:17-latest
WORKDIR /app
COPY --from=build /build/build/libs/build.jar app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "app.jar"]
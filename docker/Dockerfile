FROM amazoncorretto:21
WORKDIR /app
COPY ./target/orquestador-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# docker build -t ms-usuario-orquestador -f docker/Dockerfile .
# docker run -d --name usuario-orquestador -p 8080:8080 --network usuario_network ms-usuario-orquestador

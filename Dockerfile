# Importa a jre21
FROM eclipse-temurin:21-jre-alpine

# Defini o nome da api
ENV APP_NAME=api-user-management

# Crie um usuário para segurança
RUN adduser -D app

# Defina o usuário padrão como "app"
USER app

LABEL maintainer="lopes"
COPY ./build/libs/$APP_NAME-1.0.0.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
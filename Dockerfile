# Primeira etapa do build para gerar o JAR utilizando o Maven
FROM eclipse-temurin:21-jdk-alpine as BUILD_IMAGE
# Copiar o código-fonte para o container
COPY . /app
# Definir o diretório de trabalho do container
WORKDIR /app
# Gerar o JAR usando o Maven e não executar os testes para agilizar o processo de build
RUN cd /app && ./mvnw clean package -DskipTests

# Segunda etapa do build para gerar a imagem do container com o JAR
FROM eclipse-temurin:21-jre-alpine
# Copiar o JAR gerado na primeira etapa para o container
COPY --from=BUILD_IMAGE /app/target/*.jar /app/application.jar
# Definir o diretório de trabalho do container
WORKDIR /app
# Definir o comando para executar o JAR
CMD ["java", "-jar", "application.jar"]
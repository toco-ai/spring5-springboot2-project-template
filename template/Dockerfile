FROM eclipse-temurin:17.0.16_8-jdk-noble AS builder
WORKDIR source
COPY ./ ./
ARG JAR_FILE=entrance/web/target/*.jar
RUN ./mvnw clean package -Dmaven.test.skip=true
RUN cp ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar ./app.jar extract

FROM eclipse-temurin:17.0.16_8-jdk-noble
WORKDIR /application
COPY --from=builder source/dependencies/ ./
COPY --from=builder source/snapshot-dependencies/ ./
COPY --from=builder source/spring-boot-loader/ ./
COPY --from=builder source/application/ ./
ENV JAVA_OPTS="-Xms1024m -Xmx1024m"
ENV MAIN_CLASS="org.springframework.boot.loader.JarLauncher"
ENTRYPOINT ["sh", "-c", "exec java ${JAVA_OPTS} -Djava.security.egd=file:dev/./urandom ${MAIN_CLASS}"]

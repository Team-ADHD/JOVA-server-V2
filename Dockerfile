FROM eclipse-temurin:23-jdk AS builder
ENV TZ=Asia/Seoul
ENV LANG=ko_KR.UTF-8
ENV LANGUAGE=ko_KR:ko
ENV LC_ALL=ko_KR.UTF-8
RUN apt-get update && apt-get install -y \
    git \
    curl \
    unzip \
    && apt-get clean
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test
FROM eclipse-temurin:23-jre
ENV TZ=Asia/Seoul
ENV LANG=ko_KR.UTF-8
ENV LANGUAGE=ko_KR:ko
ENV LC_ALL=ko_KR.UTF-8
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
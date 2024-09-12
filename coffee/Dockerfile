# 빌드 스테이지
FROM maven:3.8.5-openjdk-17 AS builder

# 작업 디렉토리 설정
WORKDIR /app

# Maven 프로젝트 파일 복사
COPY pom.xml .
COPY src ./src

# 의존성 다운로드 및 애플리케이션 빌드
RUN mvn clean package -DskipTests

# 실행 스테이지
FROM openjdk:17-jdk-slim

# 빌드 스테이지에서 생성된 JAR 파일을 복사
COPY --from=builder /app/target/*.jar app.jar

# 애플리케이션 설정 파일 복사
COPY ./src/main/resources/application-test.properties application.properties

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]

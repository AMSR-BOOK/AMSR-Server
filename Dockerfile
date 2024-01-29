# Dockerfile

# jdk17 Image Start
FROM openjdk:17

# 인자 설정 - JAR_File
ARG JAR_FILE=/build/libs/*-SNAPSHOT.jar

# TimeZone 설정
ENV TZ=Asia/Seoul
ENV	SPRING_PROFILE local

# jar 파일 복제
COPY ${JAR_FILE} app.jar

# 포트 설정
EXPOSE 8080

# 실행 명령어
ENTRYPOINT [ \
"java", \
"-Dspring.profiles.active=${SPRING_PROFILE}", \
"-Duser.timezone=Asia/Seoul", \
"-jar", \
"/app.jar" \
]
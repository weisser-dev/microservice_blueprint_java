FROM gradle:jdk8 as builder
RUN mkdir application
ADD build.gradle application
ADD settings.gradle application
ADD src application/src
WORKDIR application
RUN gradle clean build
FROM frolvlad/alpine-oraclejre8:slim
COPY --from=builder /home/gradle/application/build/libs/service-latest-all.jar /app/
#COPY --from=builder /home/gradle/application/startup.sh /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "service-latest-all.jar"]
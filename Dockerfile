FROM gradle:jdk8 as builder

# Create a new Directory called application
RUN mkdir application
# Add our gradle files there
ADD build.gradle application
ADD settings.gradle application
# And add our src directory there 
ADD src application/src

# Set the workdir to `application`
WORKDIR application

# Run gradle for creating a clean jar
RUN gradle clean build --stacktrace --info

# Now we use a clean jre8-slim img only for our application
FROM frolvlad/alpine-oraclejre8:slim
# Copy the jar there
COPY --from=builder /home/gradle/application/build/libs/Blueprint-latest.jar /app/
# Set the workdir to /app
WORKDIR /app
# Set the entrypoint
ENTRYPOINT ["java", "-jar", "Blueprint-latest.jar"]
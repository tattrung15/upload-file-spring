FROM gradle:7.1.1-jdk11-hotspot as builder
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace -x test

FROM adoptopenjdk/openjdk11:jre
WORKDIR /app
COPY  --from=builder /builder/build/libs/upload-file-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "upload-file-0.0.1-SNAPSHOT.jar"]
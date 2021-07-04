FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
VOLUME /tmp
ADD target/LoadApi.jar LoadApi.jar
ENTRYPOINT ["java","-jar","/LoadApi.jar"]

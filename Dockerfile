FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD store-1.0.jar store-1.0.jar
ENTRYPOINT ["java","-jar","store-1.0.jar"]

FROM openjdk:8
COPY  target/aadhar-service.jar aadhar-service.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","aadhar-service.jar"]
FROM openjdk:17
EXPOSE 8060
ADD target/buildlive-projectservice.jar buildlive-projectservice.jar
ENTRYPOINT ["java","-jar","/buildlive-projectservice.jar"]
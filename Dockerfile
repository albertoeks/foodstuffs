FROM java:8-jdk-alpine
COPY foodstuffs-1.0-SNAPSHOT.jar /home/foodstuffs-1.0-SNAPSHOT.jar
WORKDIR /home
EXPOSE 8080
CMD ["java","-jar","/home/foodstuffs-1.0-SNAPSHOT.jar"]

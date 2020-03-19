FROM java:8-jdk-alpine
COPY build/libs/rezdyTask-1.0-SNAPSHOT.jar /home/rezdyTask-1.0-SNAPSHOT.jar.jar
WORKDIR /home
EXPOSE 8080
CMD ["java","-jar","/home/foodstuffs-1.0-SNAPSHOT.jar"]

SET JAVA_HOME=C:/Program Files/Java/jdk1.8.0_201
REM mvn clean install

mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8701 --HOSTNAME=127.0.0.1
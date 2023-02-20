README

This is a Java Spring Boot application that calculates rewards points for transactions made by customers.

Project Structure
src/main/java contains the Java source code for the application
com.example.rewardsservice contains the main package
controller contains the REST API controllers
entity contains the JPA entity classes
repository contains the JPA repository interfaces
service contains the service classes
src/main/resources contains the application properties files and the SQL file to initialize the database
src/test/java contains the Java test classes for the application

Project Structure
src/main/java contains the Java source code for the application
com.example.rewardsservice contains the main package
controller contains the REST API controllers
entity contains the JPA entity classes
repository contains the JPA repository interfaces
service contains the service classes
src/main/resources contains the application properties files and the SQL file to initialize the database
src/test/java contains the Java test classes for the application

REST API
The application provides the following REST API endpoints:

GET /api/rewards/all returns a list of rewards for each customer and each month
GET /api/rewards/total returns a list of total rewards for each customer
Testing
To run the tests for the application:

Open the project in your Java IDE of choice
Run the tests in the src/test/java directory
Dependencies
This project uses the following dependencies:

Spring Boot
Spring Data JPA
MySQL Database
Lombok
JUnit5
Mockito

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
![Screenshot 2023-02-20 154801](https://user-images.githubusercontent.com/125827402/220197104-8f615cc6-828a-4619-be97-d4b7d4f392d7.png)
GET /api/rewards/total returns a list of total rewards for each customer
![Screenshot 2023-02-20 154829](https://user-images.githubusercontent.com/125827402/220197108-08472942-c3dc-4784-bc7f-ad74416993f1.png)

Data in MySQL DB

![Screenshot 2023-02-20 154911](https://user-images.githubusercontent.com/125827402/220197110-4e6b6ea2-4206-497c-a519-a84e8df6058d.png)
![Screenshot 2023-02-20 154926](https://user-images.githubusercontent.com/125827402/220197112-344f2271-0755-4a3e-ae71-6b724a1d4b98.png)

Dependencies
This project uses the following dependencies:

Spring Boot
Spring Data JPA
MySQL Database
Lombok
JUnit5
Mockito

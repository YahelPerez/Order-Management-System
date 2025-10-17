Order Management System - MELI Challenge

This project is a web application developed with Spring Boot to manage orders for an online store. It allows performing CRUD (Create, Read, Update, Delete) operations through a RESTful API.

Technology Stack

Language: Java 17

Framework: Spring Boot 3.x

Data Access: Spring Data JPA

Build Tool: Gradle

Database (Development): H2 In-Memory Database

Prerequisites

Have Java Development Kit (JDK) 17 or higher installed.

Have Git installed (to clone the repository).

How to Run the Project

Follow these steps to get the application running on your local machine.

1. Using the Startup Script (Recommended)

The project includes a script that automates the entire compilation and execution process.

On Linux or macOS systems:

# First, grant execution permissions to the script (you only need to do this once)
chmod +x startup.sh

# Then, run the script
./startup.sh


On Windows (using a terminal like Git Bash or WSL):

./startup.sh


2. Manually with Gradle

If you prefer to run the commands yourself, you can use the Gradle Wrapper (gradlew) included in the project.

# 1. Compile the project and create the .jar file
./gradlew build

# 2. Run the generated .jar file
java -jar build/libs/order-management-0.0.1-SNAPSHOT.jar


Once executed, the server will be running at http://localhost:8080.

API Endpoints

The base URL for all resources is http://localhost:8080/api.

HTTP Method

URL Endpoint

Description

POST

/api/orders

Creates a new order.

GET

/api/orders

Gets a list of all orders.

GET

/api/orders/{id}

Gets an order by its ID.

PUT

/api/orders/{id}

Updates an existing order.

DELETE

/api/orders/{id}

Deletes an order by its ID.

Note: You can use the Meli-Order-Management.postman_collection.json collection (included in the repository) to easily test all endpoints in Postman.

H2 Database Access

For the development environment, the application uses an in-memory database (H2). You can access its web console to view tables and data at the following URL:

http://localhost:8080/h2-console

Configuration to log in to the H2 console:

Driver Class: org.h2.Driver

JDBC URL: jdbc:h2:mem:orderdb

User Name: sa

Password: (leave blank)
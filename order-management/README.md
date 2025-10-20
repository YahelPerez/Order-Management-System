# Order Management System - MELI Challenge

This project is a web application developed with Spring Boot to manage orders for an online store. It allows performing CRUD (Create, Read, Update, Delete) operations through a RESTful API.

---

## Technology Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.x
* **Data Access:** Spring Data JPA
* **Build Tool:** Gradle
* **Database (Development):** H2 In-Memory Database

---

## Prerequisites

* Have Java Development Kit (JDK) 17 or higher installed.
* Have Git installed (to clone the repository).

---

## How to Run the Project

Follow these steps to get the application running on your local machine:

### 1. Using the Startup Script (Recommended)

The project includes a `startup.sh` script that automates the entire compilation and execution process.

**On Linux or macOS systems:**
```bash
# Give execution permissions to the script
chmod +x startup.sh

# Run the script
./startup.sh
````

**On Windows (using Git Bash or a similar terminal):**

```bash
./startup.sh
```

The application will start on `http://localhost:8080`.

### 2\. Manual Execution with Gradle

If you prefer to run the project manually, use the Gradle wrapper:

```bash
# Compile and build the project
./gradlew build

# Run the application
java -jar build/libs/order-management-0.0.1-SNAPSHOT.jar
```

-----

## Environment Configuration (Sprint 2)

This project is configured to work in different environments using Spring Profiles.

### Available Profiles

* `dev` (Default): For local development. Uses an H2 in-memory database and enables the H2 console at `http://localhost:8080/h2-console`.
* `test`: For running automated tests. Can be configured to use a separate in-memory database to avoid conflicts.
* `prod`: For a production environment. Configured to connect to a PostgreSQL database.

### How to Activate a Profile

By default, the `dev` profile is active. To activate a different profile (e.g., `prod`), you can use the `spring.profiles.active` VM option when running the application:

**Example in IntelliJ:**
Go to `Run > Edit Configurations... > Modify options > Add VM options` and enter:
`-Dspring.profiles.active=prod`

**Example from the command line:**

```bash
java -jar -Dspring.profiles.active=prod build/libs/order-management-0.0.1-SNAPSHOT.jar
```

### Environment Variables for Production

The production profile (`prod`) is configured to read the database password from an environment variable to avoid exposing secrets in the code.

Before running the application with the `prod` profile, you must set the following environment variable:

* `DB_PASSWORD`: The password for the production PostgreSQL database.

**Example on Linux/macOS:**

```bash
export DB_PASSWORD="your_super_secret_password"
java -jar -Dspring.profiles.active=prod build/libs/order-management-0.0.1-SNAPSHOT.jar
```

-----

## API Endpoints

The API provides the following endpoints under the base URL `/api/orders`:

* `GET /api/orders`: Retrieves a list of all orders.
* `GET /api/orders/{id}`: Retrieves a single order by its ID.
* `POST /api/orders`: Creates a new order.
* `PUT /api/orders/{id}`: Updates an existing order.
* `DELETE /api/orders/{id}`: Deletes an order by its ID.

-----

## Postman Collection

A Postman collection is available in the root of the project (`Meli-Order-Management.postman_collection.json`) to easily test all the API endpoints.

```
```
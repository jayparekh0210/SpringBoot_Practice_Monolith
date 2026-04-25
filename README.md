# SpringBootStarter

This project is a Spring Boot application designed as a starter template. It demonstrates a basic setup for a web application with RESTful APIs, data persistence using Spring Data JPA, and an in-memory H2 database.

## Table of Contents

- [SpringBootStarter](#springbootstarter)
  - [Table of Contents](#table-of-contents)
  - [Technologies Used](#technologies-used)
  - [Prerequisites](#prerequisites)
  - [Getting Started](#getting-started)
    - [Clone the Repository](#clone-the-repository)
    - [Build the Project](#build-the-project)
    - [Run the Application](#run-the-application)
  - [Configuration](#configuration)
  - [Database](#database)
  - [Project Structure](#project-structure)
  - [API Endpoints](#api-endpoints)
  - [Contributing](#contributing)
  - [License](#license)

## Technologies Used

*   **Java**: Version 25
*   **Spring Boot**: 4.0.5
*   **Spring WebMVC**: For building RESTful web services.
*   **Spring Data JPA**: For database interaction and object-relational mapping.
*   **H2 Database**: An in-memory relational database for development and testing.
*   **Lombok**: To reduce boilerplate code (getters, setters, constructors, etc.).
*   **Maven**: For dependency management and project build.

## Prerequisites

Before you begin, ensure you have met the following requirements:

*   **Java Development Kit (JDK)**: Version 25 or higher.
*   **Apache Maven**: Version 3.6.0 or higher.
*   **Git**: For cloning the repository.

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Clone the Repository

```powershell
git clone <repository-url>
cd SpringBootStarter
```

### Build the Project

Use Maven to build the project:

```powershell
mvn clean install
```

### Run the Application

You can run the Spring Boot application using the Maven Spring Boot plugin:

```powershell
mvn spring-boot:run
```

The application will start on `http://localhost:8080` by default.

## Configuration

The main configuration file is `src/main/resources/application.yaml`. Key configurations include:

*   `spring.application.name`: `SpringBootStarter`
*   `spring.jpa.hibernate.ddl-auto`: `update` (Hibernate will update the database schema on startup)
*   `spring.jpa.show-sql`: `true` (SQL queries will be logged)
*   `spring.h2.console.enabled`: `true` (Enables the H2 database console)
*   `spring.datasource.url`: `jdbc:h2:mem:test` (Configures an in-memory H2 database named 'test')

## Database

This project uses an H2 in-memory database. The database schema will be automatically created/updated by Hibernate on application startup.

You can access the H2 console at `http://localhost:8080/h2-console` after the application starts.
*   **JDBC URL**: `jdbc:h2:mem:test`
*   **Username**: `sa`
*   **Password**: (leave blank)

## Project Structure

The project follows a standard Spring Boot application structure, organized into several packages to promote separation of concerns:

*   `src/main/java/com/jay/springbootstarter/`:
    *   `SpringBootStarterApplication.java`: The main entry point of the application, responsible for bootstrapping the Spring Boot application.
    *   `adapter/`: Contains **Converter** classes (e.g., `AddressConverter`, `CartItemConverter`, `ProductConverter`, `UserConverter`, `OrderItemConverter`). These classes are responsible for mapping between DTOs (Data Transfer Objects) and JPA entities, ensuring a clean separation between the API layer and the persistence layer.
    *   `controllers/`: This package holds the **REST Controllers** (`CartController`, `OrderController`, `ProductController`, `UserController`). These classes handle incoming HTTP requests, delegate business logic to the service layer, and return appropriate HTTP responses.
    *   `dto/`: Contains **Data Transfer Objects** (DTOs) which are used for request and response payloads.
        *   `requests/`: DTOs used for incoming request bodies (e.g., `CartRequest`, `CreateUserRequest`, `ProductRequest`).
        *   `responses/`: DTOs used for outgoing response bodies (e.g., `CartResponse`, `CreateUserResponse`, `OrderResponse`, `ProductResponse`).
    *   `models/`: This package defines the **JPA Entities** (`Address`, `CartItem`, `Order`, `OrderItem`, `Product`, `User`, `OrderStatus`, `UserRole`). These classes represent the database tables and define the relationships between them. They are annotated with `@Entity`, `@Id`, `@GeneratedValue`, `@ManyToOne`, `@OneToMany`, etc.
    *   `repositories/`: Contains **Spring Data JPA Repositories** (`CartItemRepository`, `OrderRepository`, `ProductRepository`, `UserRepository`). These interfaces extend `JpaRepository` and provide methods for performing CRUD (Create, Read, Update, Delete) operations and custom queries on the entities.
    *   `services/`: This package contains the **Service Layer** (`CartItemService`, `OrderService`, `ProductService`, `UserService`). These classes encapsulate the business logic of the application. They interact with repositories to perform data operations and orchestrate complex workflows.
    *   `validations/`: Contains custom validation logic (e.g., `CartValidation`).
*   `src/main/resources/`:
    *   `application.yaml`: Application configuration, including database settings, server port, and other Spring Boot properties.
*   `src/test/`: Contains unit and integration tests for the application.

## API Endpoints

The following RESTful API endpoints are available:

### User Endpoints (`/api/user`)

*   **`GET /api/user/getAll`**
    *   **Description**: Retrieves a list of all registered users.
    *   **Response**: `200 OK` with a list of `CreateUserResponse` objects.
*   **`POST /api/user/create`**
    *   **Description**: Creates a new user.
    *   **Request Body**: `CreateUserRequest` (e.g., username, password, email).
    *   **Response**: `201 Created` with the created `CreateUserResponse` object.
*   **`GET /api/user/get/{id}`**
    *   **Description**: Retrieves a user by their ID.
    *   **Path Variable**: `id` (Long) - The ID of the user to retrieve.
    *   **Response**: `200 OK` with the `CreateUserResponse` object if found, `404 Not Found` otherwise.
*   **`PATCH /api/user/update/{id}`**
    *   **Description**: Updates an existing user's information.
    *   **Path Variable**: `id` (Long) - The ID of the user to update.
    *   **Request Body**: `CreateUserRequest` (fields to update).
    *   **Response**: `200 OK` with `true` if the update was successful, `404 Not Found` otherwise.

### Product Endpoints (`/api/product`)

*   **`POST /api/product/create`**
    *   **Description**: Creates a new product.
    *   **Request Body**: `ProductRequest` (e.g., name, description, price, quantity).
    *   **Response**: `201 Created` with the created `ProductResponse` object.
*   **`GET /api/product/get/{id}`**
    *   **Description**: Retrieves a product by its ID.
    *   **Path Variable**: `id` (Long) - The ID of the product to retrieve.
    *   **Response**: `200 OK` with the `ProductResponse` object if found, `404 Not Found` otherwise.
*   **`GET /api/product/getAll`**
    *   **Description**: Retrieves a list of all products.
    *   **Response**: `200 OK` with a list of `ProductResponse` objects.
*   **`PATCH /api/product/update/{id}`**
    *   **Description**: Updates an existing product's information.
    *   **Path Variable**: `id` (Long) - The ID of the product to update.
    *   **Request Body**: `ProductRequest` (fields to update).
    *   **Response**: `200 OK` with the updated `ProductResponse` object if found, `404 Not Found` otherwise.
*   **`DELETE /api/product/delete/{id}`**
    *   **Description**: Deletes a product by its ID.
    *   **Path Variable**: `id` (Long) - The ID of the product to delete.
    *   **Response**: `204 No Content` if successful, `404 Not Found` otherwise.
*   **`GET /api/product/search`**
    *   **Description**: Searches for products by a keyword.
    *   **Query Parameter**: `keyword` (String) - The keyword to search for in product names or descriptions.
    *   **Response**: `200 OK` with a list of matching `ProductResponse` objects.

### Cart Endpoints (`/api/cart`)

*   **`POST /api/cart/addToCart`**
    *   **Description**: Adds a product to the user's cart.
    *   **Request Header**: `X-User-Id` (String) - The ID of the user.
    *   **Request Body**: `CartRequest` (e.g., productId, quantity).
    *   **Response**: `201 Created` if successful, `400 Bad Request` if product is out of stock, invalid product ID, or user not found.
*   **`DELETE /api/cart/removeFromCart/{productId}`**
    *   **Description**: Removes a specific quantity of a product from the user's cart.
    *   **Request Header**: `X-User-Id` (String) - The ID of the user.
    *   **Path Variable**: `productId` (String) - The ID of the product to remove.
    *   **Response**: `204 No Content` if successful, `400 Bad Request` if product not found in cart or user not found.
*   **`DELETE /api/cart/deleteCartItem/{productId}`**
    *   **Description**: Deletes all instances of a specific product from the user's cart.
    *   **Request Header**: `X-User-Id` (String) - The ID of the user.
    *   **Path Variable**: `productId` (String) - The ID of the product to delete.
    *   **Response**: `204 No Content` if successful, `400 Bad Request` if product not found in cart or user not found.
*   **`GET /api/cart/getCartItems`**
    *   **Description**: Retrieves all items in the user's cart.
    *   **Request Header**: `X-User-Id` (String) - The ID of the user.
    *   **Response**: `200 OK` with a list of `CartResponse` objects if found, `404 Not Found` otherwise.

### Order Endpoints (`/api/order`)

*   **`POST /api/order/create`**
    *   **Description**: Creates a new order from the user's current cart.
    *   **Request Header**: `X-User-Id` (String) - The ID of the user.
    *   **Response**: `201 Created` with the created `OrderResponse` object if successful, `400 Bad Request` if user not found or cart is empty.

## Contributing

Contributions are welcome! Please feel free to fork the repository, make your changes, and submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE) - see the `LICENSE` file for details.


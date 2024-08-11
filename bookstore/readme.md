To meet the requirements you've outlined, we'll need to design a system that includes the specified functionalities while adhering to the technical and evaluation criteria. Below is a structured documentation that outlines the design, implementation, and testing of the system.

---

# System Design and Implementation Documentation

## Overview

The system is designed to manage books, users, and orders through a RESTful API interface. It will support core functionalities such as book management, user management, and order processing. The system will be implemented using Java and Spring Boot, which provides a robust framework for building web applications. We will use an in-memory data storage solution for simplicity.

## Architecture

### Components

1. **Dao Layer**: In-memory storage using `H2` to store books, users, and orders.
2. **Service Layer**: Business logic for CRUD operations on books, users, and orders.
3. **Controller Layer**: RESTful API endpoints to interact with the service layer.
4. **Exception Handling**: Custom exceptions and error responses.
5. **interceptor**: Logging of critical events and errors. Global error handling. Token authorization and auto refresh ability. Trace generator.
6. **Testing**: JUnit tests for each component and integration tests for the API.
7. **util**: Common Utilities and constants. Common converter for converting data types, such as converting a String to a Date .
8. **repository**: Data repository reading data from dao layer or cache.
9. **cache**: Caffeine cache for caching book and user data.


### Data Layer
- using mybatis for object relation mapping
- using h2 to storage data

### Service Layer

- **BookService**: Manages CRUD operations for books. 
- **UserService**: Handles user registration, login, and viewing personal information.
- **OrderService**: Processes orders, including creating, canceling, and checking order status.

### Controller Layer

- **BookController**: REST endpoints for book management.
- **UserController**: REST endpoints for user management.
- **OrderController**: REST endpoints for order processing.

### interceptor

- **LogAspect**: Log requests and response with traceId.
- **GlobalExceptionHandlerAdvice**: Global Exception handler.
- **TokenValidateInterceptor**: Token authorization.
- **TokenAutoRefresherInterceptor**: Auto refreshing token when it about to be expired.
- **TraceFilter**: Generate traceId for logging.


### Logging

- Use `org.slf4j.Logger` and `Logback` for logging important events and errors.

### Testing

- **Unit Tests**: JUnit tests for each component.
- **Integration Tests**: Using `SpringBoot Test` for testing API endpoints.

## Implementation Details

### Technology Stack

- **Language**: Java
- **Framework**: Spring Boot
- **Database**: In-memory `H2` database
- **Testing**: JUnit and SpringBoot Test
- **Build Tool**: Maven

### RESTful API Endpoints

#### Book Management

- **GET** `/book`: List all books.
- **GET** `/book/{id}`: Get a book by ID.
- **POST** `/book`: Add a new book.
- **PUT** `/book/{id}`: Update a book.
- **DELETE** `/book/{id}`: Delete a book.
- **PUT** `/book/{stockAmount}`: Update a book stock.
- **PUT** `/book/status/{id}`: Update a book status.
- **PUT** `/book/{pageNumber}/{pageSize}`: Page query.


#### User Management

- **POST** `/user/register`: Register a new user.
- **POST** `/user`: Log in a user.
- **GET** `/user/{id}`: View user's personal information.
- **PUT** `/user`: Update user's personal information.


#### Order Processing

- **POST** `/orders`: Create a new order.
- **PUT** `/orders/{id}/{event}`: operate an order.
- **GET** `/orders/{id}`: query an order info.

### Logging

Logs will be written to the console and to a file using the SLF4J API and Logback implementation.

### Testing

- **JUnit Tests**: Each component will have its own set of unit tests.
- **Integration Tests**: `MockMvc` will be used to test the functionality of the API endpoints.

## Running and Testing the Program

### Setup

1. Clone the repository or download the source code.
2. Install Java and Maven.
3. Run the application using `idea` or `ecplipse`

### Testing

1. **Unit Tests**: Run JUnit tests using `idea` or `ecplipse`.

2. **Integration Tests**: Run the application and use a tool like Postman to send requests to the API endpoints.

### Documentation

- **README**: A detailed README file will be provided with instructions on how to run and test the application.

## Conclusion

The system described above meets the requirements for book, user, and order management through a RESTful API interface. It is designed to be scalable and maintainable, with clear separation of concerns between layers. The system also includes comprehensive exception handling, logging, and testing to ensure reliability and ease of maintenance.

---

This documentation provides a high-level overview of the system design and implementation. For more detailed information, you would need to review the actual source code and the project's documentation files.
# Expense Tracker Spring Boot APIs

This repository contains the backend implementation of an Expense Tracker application using Spring Boot. The APIs allow users to track their expenses, generate expense reports, and manage their accounts.

## Technology Used

- *Spring Boot*: The application is built using the Spring Boot framework, which provides a robust and efficient way to develop and deploy Java applications.
- *MySQL Database*: The data is stored in a MySQL database, which provides a reliable and scalable solution for managing user data and expenses.
- *JSON Web Tokens (JWT)*: JWT is used for user authentication and authorization. It allows secure token-based authentication without the need for session management.
- *Java Persistence API (JPA)*: JPA is used for object-relational mapping, enabling seamless interaction with the MySQL database using Java entities.
- *Java Validation API (Bean Validation)*: Bean Validation is used for data validation, ensuring that the user inputs meet the required constraints.
- *Maven*: Maven is used as the build automation tool to manage dependencies and build the project.
- *RESTful API*: The application follows RESTful API principles for designing the endpoints and handling HTTP methods.

## Database Design

The MySQL database is designed to store user information and expense records. The database schema consists of two main tables:

1. *User Table*: This table stores user information, including the user's ID, username, password, and access token.

   | Column    | Type     | Constraints     | Description                             |
      |-----------|----------|-----------------|-----------------------------------------|
   | id        | BIGINT   | PRIMARY KEY     | Unique ID of the user                   |
   | username  | VARCHAR  | UNIQUE, NOT NULL| Username of the user                    |
   | password  | VARCHAR  | NOT NULL        | Hashed password of the user             |
   | token     | VARCHAR  |                 | Access token for user authentication    |

2. *Expense Table*: This table stores the details of individual expenses, including the expense ID, title, description, price, date, time, and the associated user ID.

   | Column      | Type      | Constraints     | Description                            |
      |-------------|-----------|-----------------|----------------------------------------|
   | id          | BIGINT    | PRIMARY KEY     | Unique ID of the expense               |
   | title       | VARCHAR   | NOT NULL        | Title of the expense                   |
   | description | VARCHAR   |                 | Description of the expense             |
   | price       | DOUBLE    | NOT NULL        | Price of the expense                   |
   | date        | DATE      | NOT NULL        | Date of the expense                    |
   | time        | TIME      |                 | Time of the expense                    |
   | user_id     | BIGINT    | FOREIGN KEY     | ID of the user who created the expense |

## Endpoints

The application provides a set of RESTful API endpoints for user authentication, managing expenses, and generating expense reports. The following endpoints are available:

### User Authentication and Authorization

1. *Sign Up*
    - Method: POST
    - Endpoint: `/api/signup`
    - Payload:
      json
      {
      "username": "your_username",
      "password": "your_password"
      }

    - Description: Creates a new user account with a unique username and password.

2. *Log In*
    - Method: POST
    - Endpoint: `/api/login`
    - Payload:
      json
      {
      "username": "your_username",
      "password": "your_password"
      }

    - Description: Authenticates the user and generates a unique access token for further API calls.

3. *Get User Details*
    - Method: GET
    - Endpoint: `/api/users/{username}`
    - Description: Retrieves user details for the specified username.

### Expense Management

4. *Create Expense*
    - Method: POST
    - Endpoint: `/api/expenses`
    - Payload:
      json
      {
      "title": "Expense Title",
      "description": "Expense Description",
      "price": 50.0,
      "date": "yyyy-MM-dd",
      "time": "HH:mm",
      "user": {
      "id": 1,
      "username": "your_username",
      "password": "your_password"
      }
      }

    - Description: Creates a new expense entry for the authenticated user.

5. *Update Expense*
    - Method: PUT
    - Endpoint: `/api/expenses/{expenseId}`
    - Payload:
      json
      {
      "title": "Updated Expense Title",
      "description": "Updated Expense Description",
      "price": 100.0,
      "date": "yyyy-MM-dd",
      "time": "HH:mm",
      "user": {
      "id": 1,
      "username": "your_username",
      "password": "your_password"
      }
      }

    - Description: Updates an existing expense entry for the authenticated user.

6. *Delete Expense*
    - Method: DELETE
    - Endpoint: `/api/expenses/{expenseId}`
    - Description: Deletes an expense entry with the specified ID for the authenticated user.

7. *Get All User Expenses*
    - Method: GET
    - Endpoint: `/api/expenses`
    - Description: Retrieves all expenses for the authenticated user.

8. *Get Expense by ID*
    - Method: GET
    - Endpoint: `/api/expenses/{expenseId}`
    - Description: Retrieves the details of a specific expense
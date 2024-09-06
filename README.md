Spring Boot application includes:

Spring Security: Configured for role-based authentication.
Swagger: Integrated for API documentation and testing.
User Management: CRUD operations on /users/** endpoints secured by Spring Security.
Database: h2 (Inmemory database)
Prerequisites
Java: Ensure you have JDK 11 or above installed.
Maven: Ensure Maven is installed to manage dependencies.

steps to clone the repo:
clone the project
git clone <repository-url>
cd <repository-directory>

Build the project
mvn clean install

Run the Application
mvn spring-boot:run

Swagger:
http://localhost:8086/swagger-ui.html

Testing the Application:
Open http://localhost:8086/swagger-ui.html
Username: admin
Password: password

Perform the crud Operations
Post:
Method: POST
Body: JSON with user details
http://localhost:8086/users/save

Put:
Method: PUT
Body: JSON with updated user details
http://localhost:8086/users/update

Get:
Method: Get
http://localhost:8086/users/1

Delete:
Method: Delete
http://localhost:8086/users/1

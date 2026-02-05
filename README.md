Customer Management System (Spring Boot)

A simple web app for managing customers with CRUD, search, and pagination.

Tech Stack: Spring Boot, Spring Data JPA, Thymeleaf, MySQL, Bootstrap 5

Setup & Run
**1. Database**
CREATE DATABASE customer_db;

CREATE USER 'customer_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON customer_db.* TO 'customer_user'@'localhost';
FLUSH PRIVILEGES;

**2. Configure Application**

src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/customer_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=customer_user
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update

**3. Run Project**

IDE: Run CustomerManagementApplication

Using VS Studio Code

Terminal:

mvn clean install
cd customer-managemenet
mvn spring-boot:run

Open in browser: http://localhost:8080/customers

**Usage**
Feature	Action

Add Customer	


Fill form → Click Add Customer

Edit Customer	


Click Edit → Update → Click Update Customer

Delete Customer	


Click Delete → Confirm


Search	
Type keyword → Click Search
Pagination	
Use Previous/Next buttons

**Validation Rules**
Name: Required
Email: Required, valid format
Phone: Required, numeric, optionally +

**Screenshots**

Add/Edit Customer Form

Customer List Table

Pagination & Search


Replace screenshots/... with actual screenshots from your app if possible.

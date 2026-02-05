# 📋 Customer Management System

A simple **Spring Boot Customer Management System** that supports full
CRUD operations, search, and pagination with a clean Bootstrap UI.

This project demonstrates good backend structure using
**Controller--Service--Repository pattern**, DTO usage, validation, and
database integration.

------------------------------------------------------------------------

# 🚀 Features

✅ Add new customers\
✅ Edit existing customers\
✅ Delete customers\
✅ View customers with pagination\
✅ Search by name or email\
✅ Form validation (Name, Email, Phone)\
✅ Bootstrap UI with toast notifications\
✅ DTO pattern implementation\
✅ Clean layered architecture

------------------------------------------------------------------------

# 🛠 Tech Stack

-   **Java 21**
-   **Spring Boot**
-   **Spring Data JPA**
-   **Thymeleaf**
-   **MySQL**
-   **Bootstrap 5**
-   **Maven**

------------------------------------------------------------------------

# ⚙️ Prerequisites

-   Java 21+
-   Maven 3+
-   MySQL 8+
-   Git
-   IDE (VS Code)

------------------------------------------------------------------------

# 🗄 Database Setup

``` sql
CREATE DATABASE customer_db;
CREATE USER 'customer_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON customer_db.* TO 'customer_user'@'localhost';
FLUSH PRIVILEGES;
```

------------------------------------------------------------------------

# 🔧 Configuration

Edit:

    src/main/resources/application.properties

### Database

``` properties
spring.datasource.url=jdbc:mysql://localhost:3306/customer_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=customer_user
spring.datasource.password=password
```
Change username and password based on your username and password of mysql.

### JPA

``` properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Thymeleaf

``` properties
spring.thymeleaf.cache=false
```

------------------------------------------------------------------------

# ▶️ How to Run

## Using IDE (VS Code)
Wait until all dependencies successfully installed.

## Using Terminal

``` bash
mvn clean install
mvn spring-boot:run
```
## Visit the browser

http://localhost:8080/customers

------------------------------------------------------------------------

# 📖 Usage
## Start Page

No data at all

<p float="left">
  <img src="screenshots/home.png" width="500" />
</p>

## Add Customer Record

Fill the form → Click Add Customer → Record Added Successfully

<p float="left">
  <img src="screenshots/Add1.png" width="500" />
  <img src="screenshots/Add2.png" width="500" />
</p>

---

## Edit Customer Record

Fill the form → Click Update Customer → Record Updated Successfully

<p float="left">
  <img src="screenshots/Edit1.png" width="500" />
  <img src="screenshots/Edit2.png" width="500" />
  <img src="screenshots/Edit3.png" width="500" />
  <img src="screenshots/Edit4.png" width="500" />
</p>

---

## Delete Customer

Click Delete → Confirm

<p float="left">
  <img src="screenshots/Delete1.png" width="500" />
  <img src="screenshots/Delete2.png" width="500" />
  <img src="screenshots/Delete3.png" width="500" />
</p>

---

## Search Customer

Enter keyword → Click Search → Searched name appeared successfully

<p float="left">
  <img src="screenshots/Search1.png" width="500" />
  <img src="screenshots/Search2.png" width="500" />
  <img src="screenshots/Search3.png" width="500" />  
</p>

---

## Pagination
Next page only appear after more than 10 customer record stored successfully.
Use Previous/Next buttons to navigate pages.


Previous/Next

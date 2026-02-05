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

-   **Java 17**
-   **Spring Boot**
-   **Spring Data JPA**
-   **Thymeleaf**
-   **MySQL**
-   **Bootstrap 5**
-   **Maven**

------------------------------------------------------------------------

# 📂 Project Structure

    customer-management
    │
    ├── controller
    │   └── CustomerController
    ├── service
    │   └── CustomerService
    ├── repository
    │   └── CustomerRepository
    ├── entity
    │   └── Customer
    ├── dto
    │   └── CustomerDTO
    ├── templates
    │   └── customers.html
    └── application.properties

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

### JPA

``` properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Thymeleaf

``` properties
spring.thymeleaf.cache=false
```

------------------------------------------------------------------------

# ▶️ How to Run

## Using IDE

1.  Open project\
2.  Run `CustomerManagementApplication`\
3.  Visit:

```{=html}
<!-- -->
```
    http://localhost:8080/customers

## Using Terminal

``` bash
mvn clean install
cd customer-management
mvn spring-boot:run
```

------------------------------------------------------------------------

# 📖 Usage

Add          

Fill form → Add

Edit         

Edit → Save

Delete       

Delete → Confirm

Search       

Enter keyword

Pagination   

Previous/Next

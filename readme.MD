# 🛍️ E-commerce Platform Backend

> A high-performance, scalable e-commerce backend built with Spring Boot

## 🚀 Features

### 🔐 Authentication & Security
- **JWT-based Authentication** - Secure user authentication with JSON Web Tokens
- **Role-based Access Control** - Different permissions for users, sellers, and admins
- **Password Encryption** - Secure password storage with BCrypt

### 🛒 E-commerce Functionality
- **Product Management** - CRUD operations for products
- **Shopping Cart** - Persistent cart functionality
- **Order Processing** - Complete order lifecycle management
- **Payment Integration** - Seamless RazorPay integration for secure transactions

### 🛠️ Development Features
- **Request Validation** - Comprehensive input validation
- **AOP Logging** - Detailed request/response logging with Spring AOP
- **API Documentation** - Swagger/OpenAPI documentation
- **Error Handling** - Global exception handling and meaningful error responses

## 🛠 Tech Stack

### Backend
- **Framework**: Spring Boot 3.x
- **Security**: Spring Security, JWT
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **AOP**: Spring AOP for cross-cutting concerns
- **Build Tool**: Maven

### Frontend (Client)
- **Framework**: React.js
- **State Management**: Redux Toolkit
- **Styling**: Tailwind CSS
- **UI Components**: Headless UI, Heroicons

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+
- PostgreSQL 13+
- Node.js 16+ (for frontend)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/ecommerce-backend.git
   cd ecommerce-backend
   ```

2. **Configure Database**
   - Create a PostgreSQL database
   - Update `application.properties` with your database credentials

3. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the API**
   - API will be available at `http://localhost:8080`
   - Access Swagger UI at `http://localhost:8080/swagger-ui.html`

## 📚 API Documentation

Detailed API documentation is available using Swagger UI when the application is running:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<div align="center">
  Made with ❤️ by Dusa Sai krishna
</div>

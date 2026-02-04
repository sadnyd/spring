# Spring JPA Security Analytics

A Spring Boot application demonstrating secure JPA entity relationships with analytics capabilities. Features bidirectional Student-Teacher mappings, JPQL analytics queries, Spring Security with HTTP Basic authentication, and REST APIs for querying student data by department.

## Quick Start

```bash
# Build
mvn clean package

# Run
java -jar target/spring-jpa-security-1.0.0.jar

# Access
curl -u engineer:password123 http://localhost:8080/api/v1/students/department/Computer%20Science
```

## Features

- **JPA Entities**: Student and Teacher with bidirectional One-to-Many/Many-to-One mappings
- **Analytics**: JPQL queries for filtering students by department and calculating average marks
- **Security**: In-memory HTTP Basic authentication
- **REST API**: Endpoints for querying student analytics
- **OpenAPI**: Auto-generated Swagger UI documentation
- **Database**: H2 in-memory with sample data initialization

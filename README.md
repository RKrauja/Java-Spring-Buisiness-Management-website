# Fullstack Product Management System,using Java Spring

## Features

1. Authentication Form Implementation
   - A functional authentication form with "Username" and "Password" fields
   - Displays error messages if authentication fails or if any parameters are incorrect
   - If the username and password are entered correctly, the system opens a view displaying data with CRUD (Create, Read, Update, and Delete) functionality

2. Database Setup
   - A "product" table using migrations
   - The database stores the following example data:

   | # | Product Name | Quantity | Price per Unit |
   |---|-------------|----------|----------------|
   | 1 | HDD 1TB     | 55       | 74.09         |
   | 2 | HDD SSD 512GB| 102      | 190.99        |
   | 3 | RAM DDR4 16GB| 47       | 80.32         |
   | ...| ...         | ...      | ...           |

3. User Role Management and Authorization
   - Administrator user has access to full CRUD functionality
   - Regular user can only view records (List, View)

4. Public Web API
   - Create a public (no authorization required) Web API endpoint that returns audit records from point 4 in JSON format
   - Returns the 10 latest records upon completion
   - Ability to filter records by date (from, to)

5. Product List Enhancement
   - Column "Total Price" that is calculated on the server side (backend) using the formula:
   (Quantity * Price per Unit) * (1 + VAT)
   - VAT value is loaded from a configuration file

## Technical Stack

### Backend
- **Framework**: Spring Boot
- **Language**: Java
- **Architecture**: Spring MVC

### Frontend
- **Template Engine**: Thymeleaf

### Database
- **Relational Database**: MySQL
- **ORM**: Hibernate (JPA)

### Build Tools
- **Dependency Management**: Maven

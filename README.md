# Contractor Services Management System

[![Course](https://img.shields.io/badge/Course-Database%20Management%20Systems%20I-blue)](https://github.com/Soumya98-dev/Database-Management-Systems-I)
[![University](https://img.shields.io/badge/University-Wayne%20State%20University-green)](https://wayne.edu/)

A comprehensive web-based system for managing tree-cutting contractor operations, developed as a course project for Database Management Systems I at Wayne State University.

## Project Overview

This application facilitates interactions between clients, a tree-cutting contractor (David Smith), and an administrator. It manages the entire workflow from quote requests to billing and payment, providing a complete business management solution.

The system enables:
- Clients to request quotes, negotiate terms, manage work orders, and handle bills
- The contractor to process requests, generate quotes, track orders, and handle billing
- An administrator to oversee operations and manage the database

## Features

### User Management
- **Role-based Authentication**: Separate registration and login for clients, contractor, and administrator
- **Customized Dashboards**: Different interfaces based on user roles

### Quote Management
- **Request Submission**: Clients can submit detailed tree-cutting requests with specifications and images
- **Quote Processing**: Contractor can review, reject, or provide initial quotes
- **Negotiation System**: Multi-stage negotiation process until agreement or termination

### Work Order Management
- **Contract Creation**: Automatic work order generation upon quote acceptance
- **Status Tracking**: Real-time status updates for all parties

### Billing System
- **Bill Generation**: Contractor can create bills from completed work orders
- **Payment Processing**: Clients can pay or dispute bills
- **Negotiation Workflow**: Bill revision and negotiation capabilities

### Administrative Tools
- **Database Initialization**: Reset and populate the database with sample data
- **Advanced Queries**: Pre-built business intelligence reports

### Reporting
- **Revenue Analysis**: Generate financial reports for specified periods
- **Audit Trails**: Complete evidence documentation for dispute resolution

## Tech Stack

- **Backend**: Java
- **Database**: MySQL
- **Frontend**: HTML/CSS, JavaScript
- **Web Framework**: Servlets/JSP
- **Database Connectivity**: JDBC

## Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/          # Java backend code
│   │   └── webapp/        # Web content (JSP, HTML, CSS, JS)
│   └── test/              # Test code
├── db_schema/             # SQL scripts for database schema
│   └── schema.sql
├── docs/
│   ├── E-R_Diagram.pdf    # Database design diagram
│   └── assumptions.txt    # Design assumptions and justifications
└── pom.xml                # Maven configuration
```

## Setup and Installation

### Prerequisites

- **Java JDK**: Version 11 or higher
- **MySQL Server**: Version 8.0 or higher
- **Apache Tomcat**: Version 9.0 or higher
- **Apache Maven**: Version 3.6 or higher
- **Git**

### Installation Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Soumya98-dev/Database-Management-Systems-I.git
   cd Database-Management-Systems-I
   ```

2. **Database Setup**:
   - Start your MySQL server
   - Create a new database:
     ```sql
     CREATE DATABASE contractor_services;
     ```
   - Run the schema script:
     ```bash
     mysql -u username -p contractor_services < db_schema/schema.sql
     ```
   - Update database connection details in `src/main/resources/db.properties`

3. **Build the project**:
   ```bash
   mvn clean install
   ```

4. **Deploy to Tomcat**:
   - Copy the generated WAR file from `target/` to your Tomcat's `webapps/` directory
   - Start Tomcat server

5. **Initialize the database**:
   - Access the application at `http://localhost:8080/contractor-services`
   - Log in as admin (username: `root`, password: `pass1234`)
   - Navigate to Admin Dashboard and click "Initialize Database"

## Usage Guide

### Admin User
- **Username**: root
- **Password**: pass1234
- **Features**: Database initialization, system queries, user management

### Contractor (David Smith)
- Register or use pre-initialized credentials
- **Features**: Quote management, work order tracking, bill generation, revenue reports

### Clients
- Register as a new client
- **Features**: Submit quote requests, negotiate prices, view orders, pay bills

## Workflow Example

1. Client registers and submits a quote request for tree cutting
2. Contractor reviews and responds with an initial quote
3. Client accepts or negotiates the quote
4. Upon acceptance, a work order is generated
5. After completion, contractor generates a bill
6. Client pays or disputes the bill
7. Process completes with payment or continues with negotiation

## Future Enhancements

- Mobile application development
- Integration with payment gateways
- Automated scheduling system
- Customer feedback and rating system

## License

This project was developed for academic purposes at Wayne State University.

## Contributors

- [Soumyadeep Chatterjee](https://github.com/Soumya98-dev)
- [Geetanjali Kulkarni](https://github.com/GeetKul)

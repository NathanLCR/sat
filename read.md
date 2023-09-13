# Sistema de Agendamento Telefonico

## Description

This is a Spring project built using Spring Boot and Java 17, with Docker support for containerization. The project also utilizes PostgreSQL as its database, employs Lombok and MapStruct for enhanced code readability and mapping, and integrates Flyway for database migrations.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- [Java 17](https://adoptium.net/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Getting Started

To get a local copy up and running, follow these steps:

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/NathanLCR/sat
   ```

2. Navigate to the project directory:

   ```bash
   cd sat
   ```

3. Build the project:

   ```bash
   mvn clean install
   ```

4. Run the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

The Spring application should now be up and running.

## Usage

- Access the Spring application API at `http://localhost:8080`.
- Use tools like `curl`, [Postman](https://www.postman.com/), or [Swagger](https://swagger.io/) to interact with the API endpoints.

## Project Structure

The project follows a standard Spring Boot project structure:

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.nathan.sat
│   │   │   │   ├── controller  
│   │   │   │   ├── model  
│   │   │   │   ├── repository  
│   │   │   │   ├── service  
│   │   │   │   ├── util  
│   │   ├── resources
│   ├── test
│   │   ├── java
│   │   │   ├── com.nathan.sat
│   │   │   │   ├── controller  
│   │   ├── resources
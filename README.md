# Spring Demo

This is a test project to demo a REST API for a product and stock management system using Gradle, Spring Boot, Spring
Web, Spring Data and an H2 database. The initial project was generated using Spring Initializr. The database contains an
initial testset of data for stores, products and stock that is automatically loaded.

Since this project uses Gradle-wrapper and an in-memory H2 database, it can be run without a local Gradle installation
or an installed database. The only local requirement is Java 11. Run the following command:

> mvnw clean spring-boot:run

To access the HTTP GET endpoints (or the H2 embedded GUI console) open the context root in your browser:

> http://localhost:8080/api

# Data model

# REST API

# Author

[Daniël Rutten](https://github.com/daniel-rutten)
# product-transaction-service
This Api is to add purchase orders and generate reports based on the transactions 

## Tech Stack
* Java 11
* Spring JPA
* Spring Boot
* Junit 5
* Mockito
* Flyway
* Feign Client
* H2
* Maven
* Rest

# How to start the application
- ```mvn clean install```

# How to test the application
* ## Swagger Specs
  * (http://localhost:8080/swagger-ui/index.html)

# Assumptions/Notes
* Assume report customers from australia is a business requirement and no need to parameterised.

# Product Backlog
* Need to add spring webflux for handle reactive programming support
* Need to add web security with user role and permission based
* customer report need to parameterised to get any given country 

<!-- TOC -->
* [Application Setup and Execution Guide](#application-setup-and-execution-guide)
  * [Prerequisites](#prerequisites)
  * [Setup](#setup)
  * [Execution](#execution)
* [Design choices and Technologies Employed](#design-choices-and-technologies-employed)
  * [Design](#design)
  * [Domain-Driven Design (DDD)](#domain-driven-design-ddd)
  * [Hexagonal Architecture](#hexagonal-architecture)
  * [Technologies](#technologies)
  * [Application Services](#application-services)
  * [Records](#records)
* [Entity Relationship Documentation](#entity-relationship-documentation)
  * [Book Entity](#book-entity)
    * [Relationships](#relationships)
  * [BookReservation Entity](#bookreservation-entity)
    * [Relationships](#relationships-1)
  * [BookReview Entity](#bookreview-entity)
    * [Relationships](#relationships-2)
* [Troubleshooting](#troubleshooting)
<!-- TOC -->

# Application Setup and Execution Guide
This guide provides instructions on how to set up and run the application.  

## Prerequisites
Before you begin, ensure you have met the following requirements:
* You have installed Java 21.
* You have installed Docker 25.0.3.
* This application was developed using the IntelliJ IDEA 2024.1 IDE.

## Setup
Clone the application repository to your local machine using the following command in your terminal:

```bash
git clone <repository_url>
```
Replace `<repository_url>` with the URL of your Git repository.

Change your current directory to the project's root directory with:

```bash
cd <project_directory>
```
Replace `<project_directory>` with the name of the directory where you cloned the repository.

Build the project using Gradle with the following command:
```bash
./gradlew build
```
This command compiles the Java code and packages the application into a JAR file.

## Execution
You can build the Docker image for the application using the docker build command. Here's the command:

```bash
docker build -t ximple-challenge-app:latest .
```

You can use Docker Compose to run the application. This requires a docker-compose.yml file in your project directory. Here's the command:
```bash
docker-compose up
```
This command starts the application along with any services defined in `docker-compose.yml` file.

>[!WARNING]
If you change the name of docker image in the Dockerfile, you need to update the image name in the docker-compose.yml file as well.

# Design choices and Technologies Employed

## Design
The application is designed using Domain-Driven Design (DDD) and Hexagonal Architecture.

## Domain-Driven Design (DDD)
DDD is an approach to software development that centers the development on programming a domain model that has a rich understanding of the processes and rules of a domain. This approach is typically used for complex systems where the domain model and the business processes need to be in sync.  In this application, DDD is applied by having a clear separation of the domain layer (`br.challenge.ximple.domain.adapters`) from the infrastructure layer (`br.challenge.ximple.infrastracture.entities`). The domain layer contains the business logic and the business rules, while the infrastructure layer contains the technical concerns (like database access).  

## Hexagonal Architecture
Hexagonal Architecture is an architectural style that moves a developer's focus from conceptual layers to a distinction between the software's inside and outside parts. The main idea is to allow an application to equally be driven by users, programs, automated test (Despite the application not having any tests 🤪) or batch scripts, and to be developed and tested in isolation from its eventual run-time devices and databases.  

In this application, Hexagonal Architecture is applied by having clear separations between the application, domain, and infrastructure layers. The application layer is where the use cases are implemented. The domain layer contains the business logic and the business rules. The infrastructure layer contains the technical concerns (like database access).  

## Technologies
The application uses the following technologies:  

* Java: The main programming language used in the application.
* SQL: Used for defining and manipulating the data in the database.
* Spring Boot: An open-source Java-based framework used to create stand-alone, production-grade Spring-based Applications. It is used to simplify the bootstrapping and development of a new Spring application.
* Gradle: A build automation tool focused on flexibility and performance.

## Application Services
The application has three main services, `BookService`, `BookReservationService` and `BookReviewService`, which are responsible for handling book reservations and reviews respectively. These services use repositories to interact with the database and perform operations like creating a reservation or a review.  The `BookReservationService` has methods for creating a reservation (`createReservation`) and for delivering a book (`deliveryBook`). The `BookReviewService` has a method for creating a review (`createReview`).  Each service invalidates the cache for the list of all books for search after performing its operations to ensure that the search results are always up-to-date.  

## Records
The application uses records (`CreateAndSearchReview`, `CreateReservationBook`) to represent a group of related data items. The state description, which is in the body of the class, is represented by the compact canonical constructor parameters.

# Entity Relationship Documentation
## Book Entity

The `Book` entity represents a book in the system. It contains details about the book such as title, author, description, genre, language, publisher, publication date, number of pages, rate per day, availability, reservation and reviews.

### Relationships

- `BookReservation`: A `Book` has a one-to-one relationship with `BookReservation`. This relationship is represented by the `reservation` field in the `Book` entity. The `BookReservation` entity contains the reservation details of the book.
- `BookReview`: A `Book` has a one-to-many relationship with `BookReview`. This relationship is represented by the `reviews` field in the `Book` entity. The `BookReview` entity contains the list of reviews for the book.

## BookReservation Entity

The `BookReservation` entity represents a book reservation in the system. It contains details about the reservation such as the unique identifier, client's CNPJ/CPF, expiration date, and the associated book.

### Relationships

- `Book`: A `BookReservation` has a one-to-one relationship with `Book`. This relationship is represented by the `book` field in the `BookReservation` entity. The `Book` entity contains the details of the book that is reserved.

## BookReview Entity

The `BookReview` entity represents a book review in the system. It contains details about the review such as the unique identifier, author's name, review description, rating, and the associated book.

### Relationships

- `Book`: A `BookReview` has a many-to-one relationship with `Book`. This relationship is represented by the `book` field in the `BookReview` entity. The `Book` entity contains the details of the book that is reviewed.

# Troubleshooting

Firewall Settings: Ensure that your firewall is not blocking the connection to port 1521.

Check Firewall Settings: Check the firewall settings on both the Windows machine and the Linux server. You might need to configure the firewalls to allow ICMP packets.

>[!NOTE]
> H2 database is running inside a Docker container, you might face some issues because Docker containers have their own network by default.

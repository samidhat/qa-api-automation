# API Automation Framework

Designed and developed to explore and implement API automation testing using **REST Assured**, **Java**, **TestNG**, **Extent Reports**, and **Log4j2** while following industry-standard automation framework practices.

## Tech Stack
* **Language:** Java
* **API Testing Library:** REST Assured
* **Test Runner:** TestNG
* **Build & Dependency Management:** Maven
* **Reporting:** Extent Reports (via TestNG Listeners)
* **Logging:** Log4j2
* **Data Management:** Excel / DataProviders

## Framework Features
* **Hybrid Framework Architecture:** Separates routes, endpoint methods (`UserEndPoints`), POJO payloads (`User`), test classes, and utility helpers.
* **Dual Endpoint Routing:** Supports reading API routes from both Java constants (`Routes.java`) and external property files (`route.properties`).
* **Data-Driven Testing:** Executes multiple test iterations using external datasets stored in the `testData` directory (`DataDrivenTests.java`).
* **Automated HTML Reporting:** Integrates `ExtentReportListener.java` to automatically generate detailed execution reports on test completion.
* **Structured Logging:** Captures request and response execution logs using `log4j2.xml`.
* **Foundational API Examples:** Includes standalone CRUD and payload creation examples under the `basics` package (`Create`, `Get`, `Update`, `PostWays`, `Pojo_PostWays`).

## Project Structure
```text
apidemo
├── src
│   ├── main
│   └── test
│       ├── java
│       │   ├── api
│       │   │   ├── endpoints
│       │   │   │   ├── Routes.java                  # Base URL and endpoint route constants
│       │   │   │   ├── UserEndPoints.java           # CRUD operations using Routes.java
│       │   │   │   └── UserEndPoints1.java          # CRUD operations using route.properties
│       │   │   ├── listeners
│       │   │   │   └── ExtentReportListener.java    # TestNG listener for Extent HTML reports
│       │   │   ├── payload
│       │   │   │   └── User.java                    # POJO class for User request/response payloads
│       │   │   ├── test
│       │   │   │   ├── DataDrivenTests.java         # Data-driven API test cases
│       │   │   │   └── UserTests.java               # Standard User CRUD API test cases
│       │   │   └── utilities                        # DataProviders, Excel utilities, and helpers
│       │   └── basics
│       │       ├── Create.java                      # Basic POST request practice
│       │       ├── Get.java                         # Basic GET request practice
│       │       ├── Pojo_PostWays.java               # Payload serialization via POJO
│       │       ├── PostWays.java                    # Different ways to build POST payloads
│       │       └── Update.java                      # Basic PUT/PATCH request practice
│       └── resources
│           ├── log4j2.xml                           # Log4j2 logging configuration
│           └── route.properties                     # Externalized API endpoint URLs
├── testData                                         # External Excel/data files for data-driven tests
├── target                                           # Compiled classes and Maven build outputs
├── pom.xml                                          # Maven dependencies and build configuration
├── testng.xml                                       # TestNG suite execution configuration
└── README.md
```

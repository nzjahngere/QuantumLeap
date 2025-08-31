# QuantumLeap: E-Commerce Test Automation Framework

## Introduction
QuantumLeap is a **professional Test Automation Framework** designed to validate the functionality, performance, and security of a live e-commerce web application and its supporting APIs.  
This framework demonstrates expertise in **Selenium UI automation, TestNG, BDD with Cucumber, API testing with REST Assured, reporting with Extent Reports**, and fundamental **non-functional testing concepts**.

**Applications Under Test (AUT):**  
- **Web Application:** [Sauce Labs Demo E-Commerce Site](https://www.saucedemo.com/)  
- **REST API:** [ReqRes Hosted REST API](https://reqres.in/)

## Project Type
SDET / Test Automation Framework (Fullstack Testing: UI + API)

## Directory Structure

```
The Quantum Leap/
├─ manual-testing-docs
├─ performance-test
├─ QuantumLeap/
├─ QuantumLeapFeatures/
│  ├─ Cart.feature
│  ├─ Checkout.feature
│  ├─ Homepage.feature
│  └─ Login.feature
├─ reports/              # Generated HTML reports
├─ src/
│  ├─ test/
│  │  ├─ java/
│  │  │  ├─ apiTests/           # API Tests
│  │  │  ├─ quantumLeapRunner/  # Runner file
│  │  │  ├─ quantumLeapTests/   # Cucumber Step Definitions
│  └─ └─ └─ securityTests/      # Security Tests
├─ pom.xml                      # Maven project file
└─ README.md

```` 

## Features
- Automated **login** tests using multiple user scenarios (`@DataProvider`)  
- End-to-end **purchase flow automation** for e-commerce site  
- **Gherking syntax** for maintainability and reusability  
- **BDD Integration with Cucumber** for human-readable test scenarios  
- **API Automation** for CRUD operations using REST Assured  
- **Extent Reports** integration for detailed test reporting  
- **Performance & Security Testing Documentation**  

## Design Decisions & Assumptions
- Adopted **Gherking syntax** for UI maintainability  
- Used **TestNG** as the main test framework for structured execution  
- BDD with **Cucumber** ensures readable and reusable test scenarios  
- REST Assured used for **API testing** to achieve end-to-end coverage  
- **Explicit waits** and `JavaScriptExecutor` ensure robust UI interactions  

## Installation & Getting Started
1. Clone the repository:  
```bash
git clone https://github.com/nzjahngere/QuantumLeap.git
cd demorepoo
````

2. Install dependencies and run tests:

```bash
mvn clean install
mvn test
```

3. View Extent Reports: `TheQuantumLeap/` folder will contain HTML reports also.

## Usage

* Run **UI Automation Tests** via TestNG classes
* Run **BDD Tests** via Cucumber Test Runner
* Run **API Tests** via REST Assured TestNG classes

## APIs Used

* [ReqRes REST API](https://reqres.in/)

## API Endpoints

| Method | Endpoint        | Description                       |
| ------ | --------------- | --------------------------------- |
| GET    | /api/users      | Retrieve all users                |
| POST   | /api/users      | Create a new user                 |
| PUT    | /api/users/\:id | Update an existing user's details |

## Technology Stack

* **Programming Language:** Java
* **UI Automation:** Selenium WebDriver
* **Test Framework:** TestNG
* **BDD:** Cucumber
* **API Automation:** REST Assured
* **Build Tool:** Maven
* **Reporting:** Extent Reports

---

## **🤝 Contributors**
- **Nazish Jehangir** ([@nzjahngere](https://github.com/nzjahngere))  
- Special thanks to **Masai School** for guidance  

---

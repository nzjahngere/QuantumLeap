# Test Scenarios for Swag Labs (QuantumLeap Project)
---

## Project Details
- **Project Name:** QuantumLeap  
- **Client Name:** Swag Labs / Sauce Demo  
- **Created By:** Nazish Jehangir  
- **Creation Date:** Aug 19th, 2025  
- **Approved By:** Masai School  

---

## Overview
This document outlines the **high-level test scenarios** for the Sauce Demo e-commerce application. Each scenario is aligned with a test category (Functional, UI, API, Security, Performance) and maps to a set of detailed test cases defined separately.  

Total **30 test cases** are distributed across **7 scenarios**.

---

## Test Scenarios Summary

| Scenario ID | Scenario Name         | Description                                                                 | No. of Test Cases |
|-------------|----------------------|-----------------------------------------------------------------------------|-------------------|
| UAU_001     | User Authentication   | Verify proper functionality of the login feature (valid, invalid, locked out, etc.) | 5 |
| SCF_002     | Shopping Cart         | Verify cart functionality — adding, removing, updating product quantities. | 4 |
| CPF_003     | Checkout Process      | Verify the checkout process including user details, payment, and confirmation. | 6 |
| UIS_004     | UI Testing            | Verify user interface is friendly, consistent, responsive, and accessible. | 4 |
| API_005     | Backend Validation    | Validate CRUD operations using both **RestAssured** (automation) and **Postman** (manual). | 8 |
| SES_006     | Security Scenarios    | Ensure the site is not vulnerable to authentication bypass or XSS injection. | 2 |
| PFS_007     | Performance Scenarios | Verify login can handle at least 50 concurrent users within acceptable response time. | 1 |

---

## Notes
- These scenarios are **high-level** and each is elaborated into detailed **test cases** in [`Test_Cases.md`](./Test_Cases.md).  
- API, Security, and Performance are partially conceptual, aligned with project requirements.  
- Scenario-to-test case mapping ensures **traceability** and comprehensive coverage across functional and non-functional aspects.  

---

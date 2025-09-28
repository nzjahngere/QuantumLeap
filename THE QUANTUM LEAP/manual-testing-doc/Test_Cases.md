# Test Cases for the Swag Labs
---

## Document purpose
This document contains the test cases for the Swag Labs E-Commerce website (SauceDemo). It includes Functional/UI test cases for the web app, API test cases (ReqRes) for both REST Assured automation and Postman manual validation, and conceptual Security & Performance test cases.

---

## Table of Contents
1. Prerequisites
2. Legend / Conventions
3. Functional & UI Test Cases (Login / Cart / Checkout / UI)
4. API Test Cases (REST Assured / Postman)
6. Conceptual Security Test Cases
7. Conceptual Performance Test Cases

---

## 1. Prerequisites
> Make sure the following are ready before executing any test case below.

### Test environment & tools
- **Test environment:** Internet access, stable network.
- **Browser(s):** Chrome (latest), Firefox (optional). Use Chrome for automation runs.
- **Driver:** ChromeDriver matching Chrome version (on PATH or project-managed).
- **Java:** JDK 11+ installed.
- **Build tool:** Maven installed (for REST Assured + TestNG runs).
- **IDE:** VS Code / IntelliJ (optional).
- **Postman:** latest version (for manual API tests).
- **API Tools (optional):** JMeter/Locust for performance validation (conceptual).
- **Reporting:** Extent Reports (configured in the framework).

### Application under test (AUT)
- **E2E Testing:** [Sauce Demo](https://www.saucedemo.com/)
- **API (for API cases):** [ReqRes](https://reqres.inapi/users)

### Test accounts & credentials (SauceDemo demo accounts)
- Checkout the website's login page

### Test data & artifacts
- Test data files (if any) under `/resources/testdata/`
- Postman collection export (attach `.json` in `/postman/` if available)
- REST Assured: Maven dependencies configured (`rest-assured`, `testng`, `json-path`, etc.)
- Ensure test user accounts exist and the AUT is reachable.
 
---

## 2. Legend / Conventions
- **TC ID:** Unique test case identifier.
- **Category:** Functional / Negative / Exploratory / UI Validation / Conceptual.
- **Priority:** High / Medium / Low.
- Steps are written in numbered order. Pre-condition = state before starting steps.

---

## 3. Functional & UI Test Cases (Login / Cart / Checkout / UI)

| Test Case ID | Test Case Name | Category | Prerequisites | Steps to Execute | Expected Result | Priority |
|---|---|---|---|---|---|---|
| TC_UAU_001_001 | Valid Login | Functional | User is on the login page | 1. Open login page<br>2. Enter `standard_user` / `secret_sauce`<br>3. Click **Login** | Login successful; redirected to Products page | High |
| TC_UAU_001_002 | Invalid Login | Negative | User is on the login page | 1. Open login page<br>2. Enter invalid credentials<br>3. Click **Login** | Login fails; proper error message displayed | High |
| TC_UAU_001_003 | Empty Fields | Negative | User is on the login page | 1. Open login page<br>2. Leave fields blank<br>3. Click **Login** | Error message displayed indicating required fields | Medium |
| TC_UAU_001_004 | Case Sensitivity | Negative | User is on the login page | 1. Enter username/password with different case variants<br>2. Click **Login** | Login fails (case-sensitive). Proper error shown | Medium |
| TC_SCF_002_001 | Add to Cart | Functional | User is logged in & on Products page | 1. Select a product<br>2. Click **Add to cart** | Product added; cart icon/count updated | High |
| TC_SCF_002_002 | Boundary Quantity | Exploratory | User is on cart page | 1. Add maximum allowable quantity of an item<br>2. Observe system behavior | System allows max quantity without crash; validation messages if limits exceeded | Medium |
| TC_SCF_002_003 | Remove from Cart | Functional | Item present in cart | 1. Open Cart<br>2. Click **Remove** on product | Item removed; cart count updates | High |
| TC_CPF_003_001 | Proceeding to Checkout | Functional | Items in cart | 1. Click **Checkout** / **Proceed to Checkout**<br>2. Fill customer info<br>3. Submit | Checkout completes or proceeds to payment page | High |
| TC_CPF_003_002 | Price Display Accuracy | Functional | Items in cart | 1. Add items to cart<br>2. Proceed to checkout | Item prices and totals match product page and total calculation | High |
| TC_CPF_003_003 | Missing Mandatory Details | Negative | Items in cart | 1. Leave mandatory checkout fields empty<br>2. Submit | Validation errors shown for missing mandatory fields | Medium |
| TC_CPF_003_004 | Checkout with Maximum Items | Exploratory | Cart filled with max items | 1. Add max allowable items<br>2. Proceed to checkout | System handles large order (no crash); performance checked | Medium |
| TC_CPF_003_005 | Order Details & Price Calculation | Functional | Items in cart | 1. Proceed to checkout<br>2. Verify line item totals & final amount | Price calculations accurate and match product prices | Medium |
| TC_CPF_003_006 | Order Confirmation | Functional | Order placed | 1. Complete checkout<br>2. Observe confirmation page | Order confirmation message/page displayed with order details | High |
| TC_UIS_004_001 | Responsiveness across Devices | UI Validation | Website accessible on device | 1. Open site on multiple device sizes (mobile/tablet/desktop) | Layout adapts correctly across devices | High |
| TC_UIS_004_002 | Broken UI elements | UI Validation | Website UI loaded | 1. Inspect pages for broken elements, missing images, truncated text | No broken UI elements; images and elements render correctly | High |
| TC_UIS_004_003 | Consistency of fonts/colors/spacing | UI Validation | Website UI loaded | 1. Compare fonts, colors, spacing vs design guidelines | UI follows design guidelines consistently | Medium |
| TC_UIS_004_004 | Page load performance | UI Validation | Website reachable | 1. Measure page load times using devtools / Lighthouse | Page loads within acceptable limits (benchmark-dependent) | High |

---
## 4. API Test Cases (REST Assured) — ReqRes (automated)
> These test cases are to be automated with REST Assured as part of the framework.

| TC ID | Method | Endpoint | Description | Steps (automation) | Expected Result | Priority |
|---|---:|---|---|---|---|---|
| RA-001 | GET | `/api/users?page=2` | Verify retrieval of user list | 1. Send GET request to `/api/users?page=2`<br>2. Assert status code == 200<br>3. Assert response body contains `data` array and each user has `id`, `email`, `first_name`, `last_name` | 200 OK, valid user list returned | High |
| RA-002 | POST | `/api/users` | Verify user creation | 1. Send POST with JSON `{ "name":"morpheus", "job":"leader" }`<br>2. Assert status code == 201<br>3. Assert response contains `name`, `job`, and generated `id` | 201 Created, response contains payload & id | High |
| RA-003 | PUT | `/api/users/2` | Verify user update | 1. Send PUT with JSON `{ "name":"neo", "job":"chosen one" }`<br>2. Assert status code == 200<br>3. Assert response reflects updated fields (or timestamp) | 200 OK, user updated | Medium |
| RA-004 | DELETE | `/api/users/2` | Verify user deletion | 1. Send DELETE request to `/api/users/2`<br>2. Assert status code == 204 | 204 No Content (deleted) | Medium |

**REST Assured notes / prerequisites**
- Add dependencies: `rest-assured`, `json-path`, `testng` (in `pom.xml`).
- Base URI: `https://reqres.in`
- Use TestNG for assertions & reporting; include these API tests in your Maven test lifecycle.
- For POST/PUT, validate fields and response structure using JsonPath.

---

## 5. API Test Cases (Postman) — ReqRes (manual/collection)
> Manual tests executed via Postman UI or as Postman Collection run.

| TC ID | Method | Endpoint | Description | Steps (Postman) | Expected Result | Priority |
|---|---:|---|---|---|---|---|
| PM-001 | GET | `/api/users?page=2` | Verify retrieval of user list | 1. Create GET request in Postman<br>2. Send request<br>3. Inspect status & response | 200 OK, `data` array present | High |
| PM-002 | POST | `/api/users` | Verify user creation | 1. Create POST with JSON body `{ "name":"morpheus","job":"leader" }`<br>2. Send request<br>3. Inspect response | 201 Created, response contains `id` & provided payload | High |
| PM-003 | PUT | `/api/users/2` | Verify user update | 1. Create PUT request with JSON `{ "name":"neo","job":"chosen one" }`<br>2. Send request<br>3. Inspect response | 200 OK, updated data present | Medium |
| PM-004 | DELETE | `/api/users/2` | Verify user deletion | 1. Create DELETE request<br>2. Send request<br>3. Inspect status | 204 No Content | Medium |

**Postman notes**
- Export the collection and save under `/postman/` in the repo.
- Add example environment variables (e.g., `{{baseUrl}} = https://reqres.in`).
- Use Postman tests tab for assertions (status code, JSON schema checks) if desired.

---

## 6. Conceptual Security Test Cases (DOCUMENTATION / MANUAL)
> These are conceptual scenarios (not automated).

| TC ID | Scenario | Objective | Approach / Steps | Expected Result | Priority |
|---|---|---|---|---|---|
| SEC-001 | Authentication Bypass (Conceptual) | Ensure protected pages cannot be accessed w/o login | 1. Attempt direct URL navigation to `/inventory.html` or other protected resources without login<br>2. Observe response/redirect | User must be redirected to login page; access denied | High |
| SEC-002 | Reflected XSS (Conceptual) | Verify input validation for login fields | 1. Inject `<script>alert('XSS')</script>` into username or password fields<br>2. Submit form and observe execution | Input sanitized / not rendered; script should not execute; safe error handling | High |

**Security notes**
- Document attack vectors, expected remediation, and suggested mitigation (input validation, CSP, output encoding).
- If required, recommend tools for deeper tests (OWASP ZAP, Burp Suite) — but keep this conceptual for the project deliverable.

---

## 7. Conceptual Performance Test Cases (DOCUMENTATION / PLAN)
> Conceptual scenarios for performance testing.

| TC ID | Scenario | Objective | Approach (conceptual) | Measured Metrics | Priority |
|---|---|---|---|---|---|
| PERF-001 | Login under load (Conceptual) | Validate login endpoint under concurrent load | 1. Simulate 50 concurrent virtual users (VU) performing login using a load tool (JMeter/Gatling/Locust)<br>2. Ramp-up over 60s, sustain for 5 mins | Avg response time, p95, throughput (req/sec), error rate (%) | High |

**Performance notes**
- Define SLAs (e.g., 95th percentile login response < 2s).
- Capture server-side metrics if possible (CPU, memory, DB latency).

---


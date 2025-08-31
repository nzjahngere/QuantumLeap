Feature: SauceDemo Login Functionality
  The feature aims to validate the login functionality of the application.

  Background:
    Given the user is on the login page

  @Login
  Scenario Outline: Validate Login Functionality
    When the user enters username "<username>" and password "<password>"
    And clicks the Login button
    Then the system should respond with "<expRes>"

    Examples:
      | username       | password      | expRes                                              |
      | standard_user  | secret_sauce  | redirected to Products page                                 |
      | invalid_user   | invalid_pass  | Epic sadface: Username and password do not match any user in this service   |
      |                |               | Epic sadface: Username is required                          |
      | STANDARD_USER  | SECRET_SAUCE  | Epic sadface: Username and password do not match any user in this service   |

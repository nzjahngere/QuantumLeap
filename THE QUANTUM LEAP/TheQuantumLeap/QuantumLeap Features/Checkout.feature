Feature: Checkout Process
Validate the complete checkout process

  Background:
    Given user is on the login page
    When user enters credentials and click submit
    Then user is redirected to the Products page

  @Checkout
  Scenario: Proceeding to Checkout
    When user adds all products to the cart
    And user navigates to the cart page
    And user clicks on checkout button
    And user enters "Marcus" "Aurelius" "12345" as checkout information
    And user clicks continue button
    Then user should see order overview page
    And user clicks finish button
    Then user should see order confirmation message

  @PriceAccuracy
  Scenario: Price Display Accuracy
    When user adds all products to the cart
    And user navigates to the cart page
    And user clicks on checkout button
    And user enters "Caesar" "Augustus" "67890" as checkout information
    And user clicks continue button
    Then user should see item prices and totals match correctly

  @MissingDetails
  Scenario: Missing Mandatory Details
    When user adds all products to the cart
    And user navigates to the cart page
    And user clicks on checkout button
    And user leaves mandatory checkout fields empty
    And user clicks continue button
    Then user should see validation error messages

  @OrderConfirmation
  Scenario: Order Confirmation
    When user adds all products to the cart
    And user navigates to the cart page
    And user clicks on checkout button
    And user enters "Julius" "Caesar" "445566" as checkout information
    And user clicks continue button
    And user clicks finish button
    Then user should see order confirmation message

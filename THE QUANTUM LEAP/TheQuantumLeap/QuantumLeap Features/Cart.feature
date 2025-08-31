Feature: Cart Functionality

  Background: 
    Given I am on login page
    When I fill credentials and click login
    Then the system must respond as expected
    And I expect to land on homepage

  Scenario: Add product to cart
    When I add a product to the cart
    Then the cart must show 1 item

  Scenario: Add all products to cart and verify in cart
    When I add all products to the cart
    Then the cart should display the correct total number of items
    And I open the cart
    Then I should see all selected products listed in the cart

  Scenario: Remove item from cart
    Given I have added a product to the cart
    When I remove it from the cart
    Then the cart should be empty

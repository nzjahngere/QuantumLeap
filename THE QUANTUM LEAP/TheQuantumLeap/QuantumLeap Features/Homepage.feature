Feature: Homepage UI and Functionality Testing
  Validate that all products, prices, images, and sorting options are correctly displayed and functional.

  Background: 
    Given I am on the login page
    When I enter credentials and click login
    Then the system should respond as expected
    And I expect to land on the homepage

  Scenario: Check all product listings
    Then I should see all products with name, description, price, and add to cart button

  Scenario Outline: Sort products using dropdown
    When I select "<sortOpts>" from the sorting dropdown
    Then products should be sorted accordingly

    Examples: 
      | sortOpts          |
      | Name (A to Z)       |
      | Name (Z to A)       |
      | Price (low to high) |
      | Price (high to low) |

  Scenario: Add a product to cart
    When I scroll to a sepcific product
    And I click add to cart
    Then the cart should show 1 item

  Scenario: Verify footer links
    When I scroll to the footer
    And I click on each footer link
    Then I should be redirected to their respective destinations

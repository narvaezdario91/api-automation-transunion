# language: en
@catalog @search
Feature: Product Search API Automation
  As an API consumer
  I want to search for products using keywords
  So that I can filter catalog items and receive descriptive errors when criteria are missing

  @smoke @regression
  Scenario Outline: Successfully search products with a valid keyword
    Given the actor is ready to consume the Automation Exercise API
    When the actor searches products with keyword "<keyword>"
    Then the response status code should be 200
    And all returned products should match the search criteria for "<keyword>"

    Examples:
      | keyword |
      | top     |
      | tshirt  |
      | jean    |

  @regression @negative
  Scenario: Search products without providing search_product parameter
    Given the actor is ready to consume the Automation Exercise API
    When the actor searches products without providing the search parameter
    Then the response message should be "Bad request, search_product parameter is missing in POST request."
    And the response code in the body should be 400

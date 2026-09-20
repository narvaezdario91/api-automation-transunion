# language: en
@catalog @products
Feature: Products Catalog API Automation
  As an API consumer
  I want to interact with the products catalog endpoints
  So that I can retrieve the full catalog, validate contract schemas, and verify unsupported method restrictions

  @smoke @regression @contract
  Scenario: Successfully retrieve all products and validate JSON schema contract
    Given the actor is ready to consume the Automation Exercise API
    When the actor queries the complete products list
    Then the response status code should be 200
    And the response body should match the JSON schema "schemas/catalog/products_list_schema.json"
    And the catalog should contain products with valid details

  @regression @negative
  Scenario: Attempt POST to products list endpoint and receive method not supported response
    Given the actor is ready to consume the Automation Exercise API
    When the actor sends an unsupported "POST" request to the products list endpoint
    Then the response message should be "This request method is not supported."
    And the response code in the body should be 405

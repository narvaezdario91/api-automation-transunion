# language: en
@user-management
Feature: User Management API Automation
  As an API consumer
  I want to interact with the User service endpoints
  So that I can manage user records, validate schemas, and ensure business rules

  @smoke @regression
  Scenario: Successfully create a user with dynamic factory data
    Given the actor is ready to consume the API
    When the actor creates a new user with random dynamic data
    Then the response status code should be 201
    And the user response should contain a valid id and createdAt timestamp

  @regression @contract
  Scenario: Validate JSON schema contract for user creation
    Given the actor is ready to consume the API
    When the actor creates a new user with name "Dario Narvaez" and job "SDET Lead"
    Then the response status code should be 201
    And the response body should match the JSON schema "schemas/user_schema.json"

  @regression
  Scenario: Query an existing user by identifier
    Given the actor is ready to consume the API
    When the actor queries the user with identifier "2"
    Then the response status code should be 200

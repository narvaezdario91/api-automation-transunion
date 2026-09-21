# Spec Delta: account-management

## ADDED Requirements

### Requirement: Negative Coverage for Account Update
The test framework SHALL verify that attempting to update an account with an unregistered email returns an error response, and that the response complies with the standard API error response contract.

#### Scenario: Attempt to update a non-existent user account
- **WHEN** an actor sends a PUT request to `/api/updateAccount` with an email that is not registered
- **THEN** the internal responseCode SHALL not be 200
- **AND** the response body SHALL match the `schemas/common/api_response_schema.json` contract

### Requirement: Negative Coverage for Account Deletion Parameter Validation
The test framework SHALL verify that attempting to delete an account without supplying the mandatory password parameter returns a 400 Bad Request error response conforming to the standard API response contract.

#### Scenario: Attempt to delete an account without password parameter
- **WHEN** an actor sends a DELETE request to `/api/deleteAccount` without providing the password parameter
- **THEN** the internal responseCode SHALL be 400
- **AND** the response body SHALL match the `schemas/common/api_response_schema.json` contract

### Requirement: Standard API Response Contract Validation for Account Lifecycle
The test framework SHALL verify that account creation, deletion, and negative update responses conform to the standard generic `api_response_schema.json` contract.

#### Scenario: Account creation response conforms to standard schema
- **WHEN** an actor creates a new user account with valid data
- **THEN** the response body SHALL match the `schemas/common/api_response_schema.json` contract

#### Scenario: Account deletion response conforms to standard schema
- **WHEN** an actor deletes a user account with valid credentials
- **THEN** the response body SHALL match the `schemas/common/api_response_schema.json` contract

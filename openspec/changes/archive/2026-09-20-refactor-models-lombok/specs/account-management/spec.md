# Spec Delta

## MODIFIED Requirements

### Requirement: Retrieve User Details by Email via GET
The test framework SHALL verify that querying `GET /api/getUserDetailByEmail` with an existing user's email returns the user's detailed profile, matches the JSON schema contract, and deserializes into a typed domain model.

#### Scenario: Retrieve user details by email successfully
- **WHEN** an actor sends a GET request to `/api/getUserDetailByEmail` with an existing email parameter
- **THEN** the response status code SHALL be 200
- **AND** the internal responseCode SHALL be 200
- **AND** the response body SHALL match the `user_detail_schema.json` contract
- **AND** the response SHALL deserialize into a typed UserDetailResponseDto model

## ADDED Requirements

### Requirement: Standardized Fluent Builder Data Model Representation
The test framework SHALL provide fluent builder-based data models using Lombok for user account payloads and typed responses, eliminating manual getters and setters.

#### Scenario: Instantiate test account data via fluent builder
- **WHEN** a test or factory instantiates account data using the fluent builder pattern
- **THEN** the model SHALL produce valid form-urlencoded parameter mappings without manual boilerplate setters

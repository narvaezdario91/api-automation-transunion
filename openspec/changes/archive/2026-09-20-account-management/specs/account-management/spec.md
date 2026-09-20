# Spec Delta

## Purpose
Provides automated API testing capabilities for Automation Exercise user account lifecycle endpoints, covering account registration, profile updates, account deletion, user detail retrieval, and autonomous test data fixture management.

## ADDED Requirements

### Requirement: User Account Creation via POST
The test framework SHALL verify that sending valid user registration data via `POST /api/createAccount` returns a successful creation response with status code 201 and confirmation message.

#### Scenario: Create a user account successfully with valid details
- **WHEN** an actor sends a POST request to `/api/createAccount` with complete valid registration data
- **THEN** the response message SHALL be "User created!"
- **AND** the internal responseCode SHALL be 201

### Requirement: User Account Deletion via DELETE
The test framework SHALL verify that sending a DELETE request to `/api/deleteAccount` with valid email and password parameters returns a successful deletion response.

#### Scenario: Delete an existing user account successfully with valid credentials
- **WHEN** an actor sends a DELETE request to `/api/deleteAccount` with valid email and password
- **THEN** the response message SHALL be "Account deleted!"
- **AND** the internal responseCode SHALL be 200

### Requirement: User Account Profile Update via PUT
The test framework SHALL verify that sending updated profile details via `PUT /api/updateAccount` returns a successful update response.

#### Scenario: Update user account profile details successfully
- **WHEN** an actor sends a PUT request to `/api/updateAccount` with updated account fields
- **THEN** the response message SHALL be "User updated!"
- **AND** the internal responseCode SHALL be 200

### Requirement: Retrieve User Details by Email via GET
The test framework SHALL verify that querying `GET /api/getUserDetailByEmail` with an existing user's email returns the user's detailed profile and matches the JSON schema contract.

#### Scenario: Retrieve user details by email successfully
- **WHEN** an actor sends a GET request to `/api/getUserDetailByEmail` with an existing email parameter
- **THEN** the response status code SHALL be 200
- **AND** the internal responseCode SHALL be 200
- **AND** the response body SHALL match the `user_detail_schema.json` contract

### Requirement: Autonomous Test Data Precondition Fixture
The test framework SHALL provide a reusable fixture mechanism (hook or task) to verify account existence and automatically create a registered user if not present, ensuring test independence.

#### Scenario: Ensure user account exists before dependent scenario execution
- **WHEN** a test scenario requires an existing user account as a precondition
- **THEN** the framework SHALL verify user existence or create the account via `POST /api/createAccount`
- **AND** the subsequent test scenario SHALL execute with guaranteed valid test data

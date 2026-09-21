# user-authentication Specification

## Purpose
Provides automated API testing capabilities for Automation Exercise user verification and login authentication endpoints, validating positive verification, missing parameter handling, non-existent user errors, and unsupported HTTP methods.

## Requirements

### Requirement: User Login Verification with Valid Credentials
The test framework SHALL verify that submitting valid email and password credentials to `POST /api/verifyLogin` returns a successful confirmation response indicating that the user exists.

#### Scenario: Verify login successfully with valid registered credentials
- **WHEN** an actor sends a POST request to `/api/verifyLogin` with valid email and password
- **THEN** the response message SHALL be "User exists!"
- **AND** the internal responseCode SHALL be 200

### Requirement: User Login Verification Missing Email Parameter
The test framework SHALL verify that sending a login verification request without the mandatory `email` parameter returns a 400 Bad Request response with a descriptive error message.

#### Scenario: Attempt login verification without email parameter
- **WHEN** an actor sends a POST request to `/api/verifyLogin` without providing the email parameter
- **THEN** the response message SHALL be "Bad request, email or password parameter is missing in POST request."
- **AND** the internal responseCode SHALL be 400

### Requirement: Unsupported HTTP Method on Verify Login Endpoint
The test framework SHALL verify that invoking an unsupported HTTP method (DELETE) on the verifyLogin endpoint returns an appropriate error response and message.

#### Scenario: Send DELETE request to verifyLogin endpoint
- **WHEN** an actor sends a DELETE request to `/api/verifyLogin`
- **THEN** the response message SHALL be "This request method is not supported."
- **AND** the internal responseCode SHALL be 405

### Requirement: User Login Verification with Non-Existent User Details
The test framework SHALL verify that submitting non-existent or invalid credentials to `POST /api/verifyLogin` returns a 404 User not found response.

#### Scenario: Attempt login verification with invalid non-existent credentials
- **WHEN** an actor sends a POST request to `/api/verifyLogin` with invalid or non-existent email and password
- **THEN** the response message SHALL be "User not found!"
- **AND** the internal responseCode SHALL be 404

### Requirement: Login Verification Response Schema Contract
The test framework SHALL verify that a successful `POST /api/verifyLogin` response conforms to the `login_response_schema.json` JSON schema contract, ensuring the body structure is stable and documented.

#### Scenario: Verify login response matches JSON schema contract
- **WHEN** an actor sends a valid POST request to `/api/verifyLogin` with valid credentials
- **THEN** the response body SHALL match the `schemas/auth/login_response_schema.json` contract definition

### Requirement: User Login Verification Missing Password Parameter
The test framework SHALL verify that sending a login verification request without the mandatory `password` parameter returns a 400 Bad Request response conforming to the standard API response schema.

#### Scenario: Attempt login verification without password parameter
- **WHEN** an actor sends a POST request to `/api/verifyLogin` without providing the password parameter
- **THEN** the response message SHALL be "Bad request, email or password parameter is missing in POST request."
- **AND** the internal responseCode SHALL be 400
- **AND** the response body SHALL match the `schemas/common/api_response_schema.json` contract

### Requirement: Autonomous Precondition Execution for Login Verification
The test framework SHALL guarantee through Cucumber hooks that the default registered user account exists prior to executing positive login verification scenarios, preventing external state-dependent test failures.

#### Scenario: Precondition hook executes for API-07 scenario
- **WHEN** an actor runs the test scenario tagged with `@api:API-07` or `@requires_user`
- **THEN** the framework SHALL autonomously verify or create the test account before executing the verification request


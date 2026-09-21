# Spec Delta: user-authentication

## ADDED Requirements

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

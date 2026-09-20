# Spec Delta

## ADDED Requirements

### Requirement: Login Verification Response Schema Contract
The test framework SHALL verify that a successful `POST /api/verifyLogin` response conforms to the `login_response_schema.json` JSON schema contract, ensuring the body structure is stable and documented.

#### Scenario: Verify login response matches JSON schema contract
- **WHEN** an actor sends a valid POST request to `/api/verifyLogin` with valid credentials
- **THEN** the response body SHALL match the `schemas/auth/login_response_schema.json` contract definition

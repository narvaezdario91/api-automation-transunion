# Proposal: Enhance Test Coverage and Contracts

## Why

While the test suite nominally invokes all 14 Automation Exercise endpoints, exploration revealed significant coverage gaps in negative and edge cases, a broken tag expression in Cucumber setup hooks for API-07, and the absence of JSON schema contract validation for standard API error and mutation responses (`responseCode` and `message`). Addressing these gaps increases test suite resilience, prevents false positives, and verifies API contracts under adverse input conditions.

## What Changes

- **Negative & Edge Scenario Coverage**:
  - `account-management`: Implement missing scenario for account creation with omitted required fields (HTTP 400), account update with invalid/non-existent user data, and account deletion with missing required parameters.
  - `user-authentication`: Add negative scenario for login verification omitting the `password` parameter (HTTP 400).
  - `products-catalog`: Add edge-case scenario searching for non-existent keywords, validating the contract and empty result handling.
- **Fixture Hook & Precondition Alignment**:
  - Update `CommonHooks.java` and `login_verification.feature` so that the user prerequisite hook correctly targets `@api:API-07` or `@requires_user`, ensuring autonomous test fixture execution without external state flakiness.
- **Contract Schema Validation for Standard Responses**:
  - Add `schemas/common/api_response_schema.json` to define the JSON contract for endpoints returning `{ "responseCode": integer, "message": string }`.
  - Validate response bodies against this contract across negative scenarios and state-changing mutations.

## Capabilities

### Modified Capabilities
- `account-management`: Add requirements for negative account update handling and deletion parameter validation, while implementing the pending scenario for account creation without required fields.
- `user-authentication`: Add requirement for login verification missing password parameter and autonomous precondition execution for API-07.
- `products-catalog`: Add requirement for handling non-existent product search queries and contract compliance.
- `api-automation-core`: Add requirement for standard generic API response contract schema validation (`api_response_schema.json`).

## Impact

- **Feature Files**: `account_management.feature`, `login_verification.feature`, `search_product.feature`.
- **Step Definitions & Tasks**: `AccountStepDefinitions.java`, `LoginStepDefinitions.java`, `CatalogStepDefinitions.java`, `CommonHooks.java`.
- **Schemas**: New JSON Schema `src/test/resources/schemas/common/api_response_schema.json`.
- **Dependencies**: No new external dependencies required; utilizes existing RestAssured JSON schema validator and Serenity Screenplay engine.

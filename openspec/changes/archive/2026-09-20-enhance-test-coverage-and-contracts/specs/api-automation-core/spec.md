# Spec Delta: api-automation-core

## ADDED Requirements

### Requirement: Standard Generic API Response Schema Validation
The test framework SHALL provide a centralized JSON schema contract `schemas/common/api_response_schema.json` to validate responses containing standard response code and message attributes, enforcing contract compliance across error handling and mutation endpoints.

#### Scenario: Response body conforms to standard API response schema
- **WHEN** an actor validates a standard status, mutation, or error response against `schemas/common/api_response_schema.json`
- **THEN** the schema validation SHALL succeed without structural or data type violations

# Spec Delta

## ADDED Requirements

### Requirement: Centralized REST Helpers and Response Deserializers
The framework SHALL provide core reusable interactions for form-urlencoded requests, generic response deserialization into strongly-typed DTOs, and protocol-level method execution to eliminate boilerplate across domain packages.

#### Scenario: Actor deserializes response body using generic typed question
- **WHEN** an actor inspects an API response body
- **THEN** the response can be deserialized directly into any designated DTO class without custom ObjectMapper logic per domain

#### Scenario: Actor executes form-urlencoded requests via centralized core helper
- **WHEN** an actor sends form-urlencoded data to any API endpoint
- **THEN** the request headers, encoding, and parameters are handled consistently by a reusable core interaction

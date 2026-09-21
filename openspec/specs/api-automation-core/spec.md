# api-automation-core Specification

## Purpose
Provides a core API test automation architecture based on the Screenplay Pattern, Cucumber BDD, and design patterns (Builder, Factory, Strategy, Facade) for reliable API testing.

## Requirements

### Requirement: Screenplay API Execution Engine
The test automation framework SHALL allow test actors to perform HTTP requests (GET, POST, PUT, DELETE, PATCH) and query response attributes using the Screenplay pattern.

#### Scenario: Actor performs a successful API request and validates response code
- **WHEN** an actor sends an HTTP request to an endpoint with valid parameters
- **THEN** the actor can verify that the response status code matches the expected HTTP code

#### Scenario: Actor extracts and verifies response body attributes
- **WHEN** an actor performs an API query on a resource
- **THEN** the actor can assert against specific fields in the returned JSON response body

### Requirement: JSON Schema Contract Validation
The framework SHALL validate that API responses conform to predefined JSON schema definitions.

#### Scenario: Response satisfies JSON schema contract
- **WHEN** an actor validates the response against a valid JSON schema file
- **THEN** the contract validation succeeds without schema conformance errors

### Requirement: Dynamic Test Data Generation
The framework SHALL provide data factories for generating randomized, realistic, and valid/invalid test payloads without hardcoded data.

#### Scenario: Factory generates unique and valid payload
- **WHEN** a test requests a valid entity payload from the data factory
- **THEN** a complete and populated DTO with randomized realistic data is generated

### Requirement: Pluggable Authentication Strategies
The framework SHALL support multiple interchangeable authentication strategies (Bearer Token, API Key, Basic Auth) without modifying core HTTP interaction logic.

#### Scenario: Actor executes request with Bearer token strategy
- **WHEN** an actor executes an API interaction configured with a Bearer token strategy
- **THEN** the outgoing HTTP request includes the Authorization Bearer header

### Requirement: Multi-Environment Configuration and Reporting
The framework SHALL allow switching target environments dynamically and generate comprehensive Serenity BDD test execution reports.

#### Scenario: Execution against designated target environment
- **WHEN** tests are executed specifying an environment profile (e.g., dev, qa, staging)
- **THEN** the base URL and environment-specific configurations are loaded accordingly and detailed in the Serenity report

### Requirement: Modular Domain Separation and Reusable Step Definitions
The framework SHALL organize automated test components into self-contained domain packages and decouple universal API step definitions from domain-specific actions to maximize reusability across endpoints.

#### Scenario: Common HTTP status code assertion is reused across domains
- **WHEN** a test scenario executes an HTTP interaction for any domain
- **THEN** the status code verification step is resolved from a shared common step definition without duplication

#### Scenario: Domain components remain cohesive within their domain package
- **WHEN** a developer adds or updates tasks, models, or questions for a specific business entity
- **THEN** all associated components reside within the designated domain package

### Requirement: Centralized REST Helpers and Response Deserializers
The framework SHALL provide core reusable interactions for form-urlencoded requests, generic response deserialization into strongly-typed DTOs, and protocol-level method execution to eliminate boilerplate across domain packages.

#### Scenario: Actor deserializes response body using generic typed question
- **WHEN** an actor inspects an API response body
- **THEN** the response can be deserialized directly into any designated DTO class without custom ObjectMapper logic per domain

#### Scenario: Actor executes form-urlencoded requests via centralized core helper
- **WHEN** an actor sends form-urlencoded data to any API endpoint
- **THEN** the request headers, encoding, and parameters are handled consistently by a reusable core interaction

### Requirement: Scenario Traceability and External API Specification Tagging
The test automation framework SHALL provide explicit scenario traceability to external API specifications through typed Serenity tags, direct external documentation hyperlinks, and standardized scenario titles indicating the target API identifier and HTTP method.

#### Scenario: Serenity report indexes scenarios by API tag type
- **WHEN** the test suite executes with typed tags in the format `@api:API-XX`
- **THEN** Serenity BDD SHALL generate an "API" tag taxonomy in the report enabling scenario filtering by endpoint

#### Scenario: Scenarios provide direct hyperlinks to official API documentation
- **WHEN** a test scenario is annotated with an `@issue:<id>` tag mapped to the external API list base URL
- **THEN** the generated Serenity execution report SHALL display a clickable badge linking directly to the corresponding endpoint documentation anchor

#### Scenario: Scenario titles declare API identifier and HTTP method signature
- **WHEN** test scenarios are authored in Gherkin feature files
- **THEN** each scenario title SHALL begin with the standardized prefix `[API-XX] [METHOD /endpoint]`

### Requirement: Standard Generic API Response Schema Validation
The test framework SHALL provide a centralized JSON schema contract `schemas/common/api_response_schema.json` to validate responses containing standard response code and message attributes, enforcing contract compliance across error handling and mutation endpoints.

#### Scenario: Response body conforms to standard API response schema
- **WHEN** an actor validates a standard status, mutation, or error response against `schemas/common/api_response_schema.json`
- **THEN** the schema validation SHALL succeed without structural or data type violations


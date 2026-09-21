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

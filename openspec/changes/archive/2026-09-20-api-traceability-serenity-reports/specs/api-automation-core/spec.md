# Spec Delta: api-automation-core

## ADDED Requirements

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

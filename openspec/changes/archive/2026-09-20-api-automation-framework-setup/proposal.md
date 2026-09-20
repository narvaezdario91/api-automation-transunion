# Proposal

## Why

To establish a scalable, maintainable, and enterprise-grade test automation framework for API testing at TransUnion, following the Screenplay Pattern, BDD with Cucumber, clean architecture, and SOLID design principles on Java 21 and Gradle.

## What Changes

- Initialize Gradle project configured for Java 21 with Serenity BDD, Cucumber 7, Serenity REST-Assured, and JSON Schema Validator.
- Implement Screenplay Pattern architecture with core components: Actors, Abilities (`CallAnApi`), Tasks, Interactions, and Questions.
- Implement complementary design patterns:
  - **Builder Pattern**: Fluent and immutable request payloads/DTOs using Lombok.
  - **Factory Pattern**: Dynamic test data generation using Datafaker (`UserDataFactory`, etc.).
  - **Strategy Pattern**: Pluggable and decoupled authentication mechanisms (Bearer Token, API Key, Basic Auth).
  - **Facade Pattern**: High-level task orchestration for complex multi-step pre-test setups.
- Configure multi-environment execution support (`serenity.conf`) and rich reporting via Serenity Living Documentation.
- Add sample API BDD feature scenarios, step definitions, schema validations, and test runner.

## Capabilities

### New Capabilities
- `api-automation-core`: Core API automation architecture incorporating Screenplay, BDD, design patterns (Builder, Factory, Strategy, Facade), multi-environment configuration, and Serenity reporting.

### Modified Capabilities
<!-- None -->

## Impact

- **New Codebase**: Sets up the foundational structure under `src/main/java`, `src/test/java`, and `src/test/resources`.
- **Build & Dependencies**: Introduces Gradle build files (`build.gradle`, `settings.gradle`, `gradle.properties`) targeting Java 21.
- **CI/CD & Reporting**: Provides command-line execution tasks and generates HTML Serenity Reports with full request/response observability.

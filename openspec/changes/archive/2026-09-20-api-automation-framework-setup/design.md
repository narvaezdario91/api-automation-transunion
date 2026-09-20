# Design

## Context

Setting up a greenfield test automation framework for API testing using Java 21, Gradle, Serenity BDD, and Cucumber 7. The design incorporates the Screenplay Pattern as the primary test interaction architecture, augmented by Builder, Factory, Strategy, and Facade design patterns to adhere to SOLID principles.

## Goals / Non-Goals

**Goals:**
- Provide a modular, maintainable, and type-safe architecture in Java 21 for API test automation.
- Implement Screenplay building blocks (`Actor`, `Ability`, `Task`, `Interaction`, `Question`).
- Decouple data generation via Factory and Builder patterns (Lombok + Datafaker).
- Enable pluggable authentication and headers through the Strategy pattern.
- Support multi-environment configuration via `serenity.conf` and rich living documentation reports.
- Support JSON Schema validation with `json-schema-validator`.

**Non-Goals:**
- Web UI (Selenium/Playwright) or Mobile automation (this is strictly for API testing).
- Database or message queue direct integration in this initial change.

## Decisions

### 1. Build Tool: Gradle with Groovy DSL & Java 21
- **Rationale**: Faster build/test execution times compared to Maven, native Serenity BDD plugin support, and modern Java 21 language features (records, switch expressions, pattern matching).
- **Alternatives considered**: Maven (`pom.xml`) - slower build lifecycle with heavier XML verbosity.

### 2. Core Framework: Serenity BDD + Cucumber 7 + Serenity RestAssured
- **Rationale**: Serenity provides battle-tested Screenplay primitives (`net.serenitybdd.screenplay.rest.*`), automated request/response logging, and living documentation reports.
- **Alternatives considered**: Standalone RestAssured + TestNG - lacks native Screenplay DSL and living documentation out-of-the-box.

### 3. Structural Design Patterns
- **Screenplay Pattern**: Tests read as human-centric business actions (`actor.attemptsTo(CreateUser.withData(user))`).
- **Builder Pattern**: Models/DTOs use Lombok `@Builder` and `@Jacksonized` for clean JSON serialization.
- **Factory Pattern**: `*DataFactory` classes encapsulate randomized and edge-case test data creation using Datafaker.
- **Strategy Pattern**: `AuthenticationStrategy` interface allows swapping Bearer, API Key, or Basic auth dynamically.
- **Facade Pattern**: Service/Task facades encapsulate complex multi-step preconditioning workflows.

## Risks / Trade-offs

- **[Initial Learning Curve for Screenplay]** → Document code conventions and provide complete sample feature/task implementations.
- **[Dependency version compatibility]** → Pin tested compatible versions of Serenity BDD (4.x+), Cucumber 7, and RestAssured compatible with Java 21.

## Migration Plan

Not applicable (greenfield repository).

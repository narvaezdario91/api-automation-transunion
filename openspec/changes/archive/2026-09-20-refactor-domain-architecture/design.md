# Design: Modular Domain Architecture & Decoupled Step Definitions

## Context

See proposal.md for motivation. The current Automation Exercise test suite organizes classes by technical layers (`models/`, `tasks/`, `questions/`) and defines shared HTTP assertions (status code, schema matching, body message and code validations) inside `CatalogStepDefinitions.java`. `LoginStepDefinitions` and `AccountStepDefinitions` rely on those definitions, creating cross-domain coupling.

## Goals / Non-Goals

**Goals:**
- Package domain components under `com.transunion.automation.domain.<subdomain>.*` (`account`, `catalog`, `auth`).
- Group transversal framework code under `com.transunion.automation.core.*` (`auth`, `config`, `constants`, `models`, `questions`).
- Centralize API endpoints in `com.transunion.automation.core.constants.Endpoints`.
- Decouple generic steps into `com.transunion.automation.stepdefinitions.common.CommonApiStepDefinitions`.
- Maintain `CommonHooks` under `com.transunion.automation.stepdefinitions.common.CommonHooks`.
- Structure domain step definitions into domain packages:
  - `com.transunion.automation.stepdefinitions.account.AccountStepDefinitions`
  - `com.transunion.automation.stepdefinitions.catalog.CatalogStepDefinitions`
  - `com.transunion.automation.stepdefinitions.auth.LoginStepDefinitions`
- Preserve all existing Spanish Gherkin steps and tags without breaking existing feature files.
- Ensure 100% test pass rate across all 16 scenarios and 0 Checkstyle violations.

**Non-Goals:**
- Changing business logic, endpoints, or test expectations.
- Modifying build toolchain, dependencies, or Serenity plugins.

## Decisions

### 1. Domain-Driven Feature Slices under `domain.*`
- **Choice**: Group domain entities into `domain.account`, `domain.catalog`, and `domain.auth`, co-locating their models, tasks, and questions.
- **Rationale**: High cohesion. Developers working on the catalog or account lifecycle find all related artifacts in one place.
- **Alternatives Considered**: Flat technical layers (`models/`, `tasks/`, `questions/`).

### 2. Centralized Endpoints: `core.constants.Endpoints`
- **Choice**: Keep all REST endpoints in `com.transunion.automation.core.constants.Endpoints`.
- **Rationale**: Centralized source of truth simplifies environment audits and refactorings.
- **Alternatives Considered**: Distributed constants per domain.

### 3. Decoupled Common Step Definitions
- **Choice**: Move the following steps from `CatalogStepDefinitions` to `CommonApiStepDefinitions`:
  - `@Dado("que el actor está listo para consumir la API de Automation Exercise")`
  - `@Entonces("el código de estado de la respuesta debe ser {int}")`
  - `@Y("el cuerpo de la respuesta debe coincidir con el esquema JSON {string}")`
  - `@Entonces("el mensaje de respuesta debe ser {string}")`
  - `@Y("el código de respuesta en el cuerpo debe ser {int}")`
  - `@Y("el código de respuesta en el cuerpo no debe ser {int}")`
- **Rationale**: Eliminates the artificial dependency of `LoginStepDefinitions` and `AccountStepDefinitions` on `CatalogStepDefinitions`.
- **Alternatives Considered**: Duplicating steps in each definition class (causes Cucumber collision errors).

### 4. Spanish Gherkin Step Compatibility
- **Choice**: Keep all Gherkin step expressions and parameter formats unchanged.
- **Rationale**: Feature files remain unmodified while the underlying Java code is cleanly modularized.

## Risks / Trade-offs

- **[Risk] Package moves break imports across tasks or questions**  
  → *Mitigation*: Update all imports systematically and verify compilation with `./gradlew compileJava compileTestJava`.
- **[Risk] Cucumber step definition discovery failure**  
  → *Mitigation*: `ApiTestSuiteRunner` specifies `glue = "com.transunion.automation.stepdefinitions"`, which Cucumber scans recursively across `common`, `account`, `catalog`, and `auth`.
- **[Risk] Checkstyle violations in reorganized classes**  
  → *Mitigation*: Execute `./gradlew checkstyleMain checkstyleTest` to ensure zero errors with `maxWarnings = 0`.

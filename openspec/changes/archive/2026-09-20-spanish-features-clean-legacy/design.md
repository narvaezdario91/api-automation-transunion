# Design: Gherkin Localization to Spanish and Legacy Code Removal

## Context
See `proposal.md`. The framework currently has English feature files and legacy demo files from the initial template. This design focuses on localizing all Gherkin features to Spanish and pruning non-AutomationExercise code.

## Goals / Non-Goals

**Goals:**
- Localize all Cucumber features to Spanish Gherkin conventions (`# language: es`).
- Update `CatalogStepDefinitions.java` with `io.cucumber.java.es.*` annotations.
- Completely remove all legacy Reqres demo features, step definitions, tasks, facades, models, schemas, and endpoints.
- Ensure Checkstyle, build, and test suite execution pass with 100% success.

**Non-Goals:**
- Implement Phases 2 and 3 (Authentication and Account CRUD) in this change.

## Decisions

### Decision 1: Use Cucumber Spanish Annotations
- **Choice**: Import `@Dado`, `@Cuando`, `@Entonces`, `@Y` from `io.cucumber.java.es.*` in `CatalogStepDefinitions.java`.
- **Rationale**: Natural match for `# language: es` feature files in Serenity/Cucumber.

### Decision 2: Pruning Obsolete Endpoints and Environment Configurations
- **Choice**: Remove `USERS`, `USER_BY_ID`, `LOGIN`, `REGISTER` from `Endpoints.java` and `reqres` from `serenity.conf`.
- **Rationale**: Eliminates dead code and prevents configuration confusion.

## Risks / Trade-offs

- **[Risk] Broken step definition regex/expressions**:
  - *Mitigation*: Match Spanish step texts verbatim between `.feature` files and Step Definition annotations, verified via `./gradlew test`.

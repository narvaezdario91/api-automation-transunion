# Proposal: Localize Gherkin Features to Spanish and Remove Legacy Non-AutomationExercise Tests

## Why
Translate all Cucumber Gherkin feature files to Spanish (`# language: es`) for native readability and eliminate legacy sample tests (Reqres demo code) to ensure 100% of the test suite is strictly focused on Automation Exercise APIs.

## What Changes
- Translate Gherkin keywords and scenarios in `products_catalog.feature`, `brands_catalog.feature`, and `search_product.feature` to Spanish (`Característica`, `Escenario`, `Esquema del escenario`, `Ejemplos`, `Dado`, `Cuando`, `Entonces`, `Y`).
- Update `CatalogStepDefinitions.java` to bind Spanish Cucumber step keywords (`@Dado`, `@Cuando`, `@Entonces`, `@Y`).
- Remove legacy Reqres demo tests and unused components:
  - `src/test/resources/features/user/create_user.feature`
  - `src/test/java/com/transunion/automation/stepdefinitions/UserStepDefinitions.java`
  - `src/main/java/com/transunion/automation/tasks/user/`
  - `src/main/java/com/transunion/automation/tasks/facades/`
  - `src/main/java/com/transunion/automation/models/request/UserRequestDto.java`
  - `src/main/java/com/transunion/automation/models/response/UserResponseDto.java`
  - `src/main/java/com/transunion/automation/questions/UserResponseBody.java`
  - `src/main/java/com/transunion/automation/utils/data/UserDataFactory.java`
  - `src/test/resources/schemas/user_schema.json`
- Clean `Endpoints.java`, `serenity.conf`, and `ApiTestSuiteRunner.java` to remove obsolete Reqres references.

## Capabilities

### New Capabilities
<!-- No new spec capabilities introduced (refactor / localization) -->

### Modified Capabilities
<!-- No requirement changes to existing capability specifications -->

## Impact
- **Features & Steps**: Exclusively in Spanish for Automation Exercise APIs.
- **Codebase Cleanliness**: Removal of legacy sample files and single source of truth for Automation Exercise testing.

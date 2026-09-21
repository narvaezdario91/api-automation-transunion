# Proposal: Refactor Project Structure to Modular Domain Architecture

## Why

The current API test automation suite spreads domain components (account, catalog, auth) across flat technical layers (`models/`, `tasks/`, `questions/`) and defines shared HTTP assertions (status code, schema matching, body message and code validations) inside `CatalogStepDefinitions.java`, forcing `LoginStepDefinitions` and `AccountStepDefinitions` to depend on catalog definitions. Refactoring to a modular domain structure (Feature Slices: `core/`, `domain/account`, `domain/catalog`, `domain/auth`) with a dedicated `CommonApiStepDefinitions` provides clean isolation, prevents step definition duplication, and simplifies ongoing maintenance.

## What Changes

- **Establish Core Framework**:
  - Move authentication strategies to `com.transunion.automation.core.auth.*`.
  - Move shared questions (`LastResponseStatusCode`, `ResponseSchemaMatches`, `ApiResponseCode`, `ApiResponseMessage`) to `com.transunion.automation.core.questions.*`.
  - Move shared response models (`ApiResponseDto`) to `com.transunion.automation.core.models.*`.
  - Maintain centralized endpoints in `com.transunion.automation.core.constants.Endpoints`.
  - Keep configuration utilities in `com.transunion.automation.core.config.EnvironmentConfig`.

- **Modularize Domains (Feature Slices)**:
  - `com.transunion.automation.domain.account.*`: encapsulate `models` (`AccountData`, `AccountDataFactory`, `UserDetailResponseDto`, `UserDto`), `tasks` (`CreateAccount`, `DeleteAccount`, `EnsureUserExists`, `GetUserDetail`, `UpdateAccount`), and `questions` (`UserDetailResponse`).
  - `com.transunion.automation.domain.catalog.*`: encapsulate `models` (`BrandDto`, `BrandsListResponseDto`, `CategoryDto`, `ProductDto`, `ProductsListResponseDto`, `UserTypeDto`), `tasks` (`GetBrandsList`, `GetProductsList`, `SearchProduct`, `ExecuteUnsupportedMethod`), and `questions` (`BrandsListResponse`, `ProductsListResponse`).
  - `com.transunion.automation.domain.auth.*`: encapsulate `tasks` (`VerifyLogin`).

- **Decouple Step Definitions**:
  - Create `com.transunion.automation.stepdefinitions.common.CommonApiStepDefinitions` containing all generic HTTP status, JSON schema, response message, and response code validations, plus the base actor setup step.
  - Keep `CommonHooks` under `com.transunion.automation.stepdefinitions.common.CommonHooks`.
  - Restructure domain step definitions to strictly handle domain interactions:
    - `com.transunion.automation.stepdefinitions.account.AccountStepDefinitions`
    - `com.transunion.automation.stepdefinitions.catalog.CatalogStepDefinitions`
    - `com.transunion.automation.stepdefinitions.auth.LoginStepDefinitions`

- **Maintain Resource Alignment**:
  - Schemas remain organized by domain under `schemas/account/`, `schemas/catalog/`, `schemas/auth/`.
  - Features remain organized by domain under `features/account/`, `features/catalog/`, `features/auth/`.

## Capabilities

### Modified Capabilities
- `api-automation-core`: Adds architectural requirement for modular domain separation and decoupled common step definitions.

## Impact

- **Package Namespaces**: Java classes under `com.transunion.automation` move from layer packages to `core` and `domain.<subdomain>`.
- **Step Definitions**: Step definition classes move to subpackages `common`, `account`, `catalog`, and `auth`.
- **Test Runner**: `ApiTestSuiteRunner` remains unchanged because it scans `glue = "com.transunion.automation.stepdefinitions"` recursively.
- **Verification**: Checkstyle rules must pass without errors (`maxWarnings = 0`), and all 16 existing scenarios must continue to pass cleanly.

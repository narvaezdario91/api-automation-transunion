# Proposal

## Why

Following the modular domain refactor, several low-level interaction patterns and boilerplate logic remain duplicated across domains and step definitions. Specifically:
- `ExecuteUnsupportedMethod` resides in `domain.catalog` while being required by `auth`.
- Generic JSON response deserialization with `ObjectMapper` is duplicated across three domain Question classes.
- Form-urlencoded HTTP request boilerplate and `relaxedHTTPSValidation()` are repeated across five Screenplay tasks.
- Step definitions executing unsupported methods are duplicated across catalog and auth step definitions.
- Negative response code assertion in `CommonApiStepDefinitions` bypasses Screenplay matchers.

Centralizing these mechanisms into `core` and unifying step definitions will eliminate code duplication, enforce DRY principles, and improve framework maintainability.

## What Changes

- **Core Generic Deserializer**: Introduce `LastResponseBody<T>` Question in `com.transunion.automation.core.questions` to deserialize responses to any DTO class, replacing duplicate boilerplate in domain Questions.
- **Relocate `ExecuteUnsupportedMethod`**: Move `ExecuteUnsupportedMethod` to `com.transunion.automation.core.tasks` as an agnostic core task.
- **Centralized Form Interaction / Request Spec Helper**: Introduce a reusable Screenplay task or interaction (`PostForm` / `PutForm` or `SendFormRequest`) in `core.tasks` and configure global relaxed HTTPS validation.
- **Unify Unsupported Method Step Definitions**: Centralize unsupported HTTP method step definitions in `CommonApiStepDefinitions` to serve catalog, auth, and future domains.
- **Standardize Screenplay Assertion**: Refactor negative `responseCode` assertion in `CommonApiStepDefinitions` to use `ApiResponseCode.fromBody()` with Hamcrest `not(equalTo(...))`.

## Capabilities

### New Capabilities

*(None)*

### Modified Capabilities

- `api-automation-core`: Add requirement for generic reusable REST tasks and response body deserializers.

## Impact

- **Core Package**: New classes `LastResponseBody<T>` and `ExecuteUnsupportedMethod` in `com.transunion.automation.core`.
- **Domain Tasks**: `CreateAccount`, `UpdateAccount`, `DeleteAccount`, `VerifyLogin`, `SearchProduct` refactored to use centralized form submission.
- **Domain Questions**: `ProductsListResponse`, `BrandsListResponse`, `UserDetailResponse` refactored to delegate to `LastResponseBody`.
- **Step Definitions**: `CatalogStepDefinitions` and `LoginStepDefinitions` delegate unsupported method handling to `CommonApiStepDefinitions`.
- **Tests / Compatibility**: All existing 16 Cucumber scenarios in Spanish must continue passing without modifying `.feature` files.

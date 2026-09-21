# Tasks

## 1. Core Framework Organization

- [x] 1.1 Move authentication strategies to `com.transunion.automation.core.auth.*` and verify compilation
- [x] 1.2 Move generic questions (`LastResponseStatusCode`, `ResponseSchemaMatches`, `ApiResponseCode`, `ApiResponseMessage`) to `com.transunion.automation.core.questions.*` and verify compilation
- [x] 1.3 Move generic response DTO (`ApiResponseDto`) to `com.transunion.automation.core.models.*` and verify compilation
- [x] 1.4 Move `Endpoints.java` to `com.transunion.automation.core.constants.Endpoints` and verify package references
- [x] 1.5 Move `EnvironmentConfig.java` to `com.transunion.automation.core.config.EnvironmentConfig` and verify references

## 2. Domain Packaging: Account, Catalog, Auth

- [x] 2.1 Move account models (`AccountData`, `AccountDataFactory`, `UserDetailResponseDto`, `UserDto`) to `com.transunion.automation.domain.account.models.*` and update imports
- [x] 2.2 Move account tasks (`CreateAccount`, `DeleteAccount`, `EnsureUserExists`, `GetUserDetail`, `UpdateAccount`) to `com.transunion.automation.domain.account.tasks.*` and update imports
- [x] 2.3 Move account question (`UserDetailResponse`) to `com.transunion.automation.domain.account.questions.*` and update imports
- [x] 2.4 Move catalog models (`BrandDto`, `BrandsListResponseDto`, `CategoryDto`, `ProductDto`, `ProductsListResponseDto`, `UserTypeDto`) to `com.transunion.automation.domain.catalog.models.*` and update imports
- [x] 2.5 Move catalog tasks (`GetBrandsList`, `GetProductsList`, `SearchProduct`, `ExecuteUnsupportedMethod`) to `com.transunion.automation.domain.catalog.tasks.*` and update imports
- [x] 2.6 Move catalog questions (`BrandsListResponse`, `ProductsListResponse`) to `com.transunion.automation.domain.catalog.questions.*` and update imports
- [x] 2.7 Move auth tasks (`VerifyLogin`) to `com.transunion.automation.domain.auth.tasks.*` and update imports
- [x] 2.8 Remove legacy empty folders under `src/main/java/com/transunion/automation/`

## 3. Step Definitions Decoupling & Reorganization

- [x] 3.1 Create `com.transunion.automation.stepdefinitions.common.CommonApiStepDefinitions` with generic actor setup, status code, JSON schema, and response body assertions
- [x] 3.2 Move `CommonHooks.java` to `com.transunion.automation.stepdefinitions.common.CommonHooks`
- [x] 3.3 Refactor `CatalogStepDefinitions.java` to `com.transunion.automation.stepdefinitions.catalog.CatalogStepDefinitions` retaining only catalog interactions
- [x] 3.4 Move `LoginStepDefinitions.java` to `com.transunion.automation.stepdefinitions.auth.LoginStepDefinitions` and update imports
- [x] 3.5 Refactor `AccountStepDefinitions.java` to `com.transunion.automation.stepdefinitions.account.AccountStepDefinitions` and update imports

## 4. Verification & Quality Assurance

- [x] 4.1 Run `./gradlew checkstyleMain checkstyleTest` and resolve any checkstyle violations
- [x] 4.2 Run `./gradlew test aggregate` to verify all 16 scenarios across account, catalog, and auth pass successfully

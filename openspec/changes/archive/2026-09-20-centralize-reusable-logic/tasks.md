# Tasks

## 1. Core Reusable Components

- [x] 1.1 Create generic `LastResponseBody<T>` Question in `com.transunion.automation.core.questions` and verify compilation
- [x] 1.2 Move `ExecuteUnsupportedMethod` to `com.transunion.automation.core.tasks` and verify compilation
- [x] 1.3 Create reusable form request tasks (`PostForm`, `PutForm`, `DeleteWithForm`) in `com.transunion.automation.core.tasks` with UTF-8 and relaxed HTTPS validation, verifying compilation

## 2. Refactor Domain Tasks and Questions

- [x] 2.1 Refactor domain questions (`ProductsListResponse`, `BrandsListResponse`, `UserDetailResponse`) to delegate to `LastResponseBody.of(...)` and verify compilation
- [x] 2.2 Refactor account tasks (`CreateAccount`, `UpdateAccount`, `DeleteAccount`) to use centralized form tasks (`PostForm`, `PutForm`, `DeleteWithForm`)
- [x] 2.3 Refactor auth task (`VerifyLogin`) and catalog task (`SearchProduct`) to use centralized form tasks and verify compilation

## 3. Centralize Step Definitions & Assertions

- [x] 3.1 Move unsupported method step definitions from `CatalogStepDefinitions` and `LoginStepDefinitions` to `CommonApiStepDefinitions`
- [x] 3.2 Refactor negative response code assertion in `CommonApiStepDefinitions` to use Screenplay `ApiResponseCode.fromBody()` with Hamcrest `not(equalTo(...))`
- [x] 3.3 Verify step definition bindings and clean up unused imports across all step definition classes

## 4. Verification & Quality Assurance

- [x] 4.1 Run `./gradlew checkstyleMain checkstyleTest` and verify 0 checkstyle violations
- [x] 4.2 Run `./gradlew clean test aggregate` and verify all 16 scenarios pass with 100% success rate

# Tasks

## 1. Contract Schemas

- [x] 1.1 Create `src/test/resources/schemas/common/api_response_schema.json` defining `responseCode` (integer) and `message` (string) as required properties, and verify schema syntax.


## 2. Fixture Hook & Preconditions Alignment

- [x] 2.1 Update `CommonHooks.java` tag expression to `@Before(value = "@requires_user or @api:API-07", order = 1)` and add `@requires_user` to scenario `[API-07]` in `login_verification.feature`, verifying that precondition executes before the test step.


## 3. Tasks and Step Definitions

- [x] 3.1 Implement Screenplay question `EmptyProductList` or collection assertion in `CatalogStepDefinitions.java` to verify that the products array is empty.
- [x] 3.2 Add tasks and step definitions in `AccountStepDefinitions.java` for creating account without required fields, updating account with non-existent email, and deleting account without password parameter.
- [x] 3.3 Add step definition in `LoginStepDefinitions.java` for attempting login verification without the password parameter.


## 4. Feature Files & Scenario Authoring

- [x] 4.1 Update `account_management.feature` to include: attempt account creation without required fields, update account with non-existent email, delete account without password, and schema validations against `schemas/common/api_response_schema.json`.
- [x] 4.2 Update `login_verification.feature` with negative scenario for login verification omitting `password` parameter, asserting responseCode 400 and `api_response_schema.json`.
- [x] 4.3 Update `search_product.feature` with edge-case scenario searching for an unmatchable keyword, asserting status 200, `products_list_schema.json`, and empty product list.


## 5. Verification and Quality Gates

- [x] 5.1 Run `./gradlew.bat clean test aggregate` and verify all scenarios pass (0 failures).
- [x] 5.2 Run `./gradlew.bat checkstyleMain checkstyleTest` and verify 0 checkstyle violations.


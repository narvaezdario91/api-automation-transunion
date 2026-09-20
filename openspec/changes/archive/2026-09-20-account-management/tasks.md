# Tasks

## 1. Endpoints & Data Models

- [x] 1.1 Add constants `CREATE_ACCOUNT`, `DELETE_ACCOUNT`, `UPDATE_ACCOUNT`, `GET_USER_DETAIL` to `Endpoints.java` and verify compiler resolves them
- [x] 1.2 Create `AccountData` model and `AccountDataFactory` in `com.transunion.automation.models.account` with customizable defaults
- [x] 1.3 Create `src/test/resources/schemas/user_detail_schema.json` contract for user details verification

## 2. Screenplay Tasks Implementation

- [x] 2.1 Implement `CreateAccount` task in `com.transunion.automation.tasks.account` supporting POST `/api/createAccount` form-urlencoded data
- [x] 2.2 Implement `DeleteAccount` task in `com.transunion.automation.tasks.account` supporting DELETE `/api/deleteAccount` credentials
- [x] 2.3 Implement `UpdateAccount` task in `com.transunion.automation.tasks.account` supporting PUT `/api/updateAccount` profile fields
- [x] 2.4 Implement `GetUserDetail` task in `com.transunion.automation.tasks.account` supporting GET `/api/getUserDetailByEmail` query parameter
- [x] 2.5 Implement `EnsureUserExists` task and `@Before("@requires_user")` hook in `CommonHooks.java` for autonomous self-healing test data

## 3. Cucumber Features & Step Definitions

- [x] 3.1 Create `src/test/resources/features/account/account_management.feature` in Spanish (`# language: es`) covering APIs 11, 12, 13, and 14
- [x] 3.2 Implement `AccountStepDefinitions.java` binding all Spanish account management steps
- [x] 3.3 Update `ApiTestSuiteRunner.java` tags to include `@account` test execution

## 4. Verification & Quality Checks

- [x] 4.1 Run `./gradlew checkstyleMain checkstyleTest` and verify zero Checkstyle violations
- [x] 4.2 Execute test suite with `./gradlew clean test aggregate` and verify all account and regression scenarios pass


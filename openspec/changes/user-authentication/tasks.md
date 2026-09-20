# Tasks

## 1. Endpoints & Constants Setup

- [ ] 1.1 Add `VERIFY_LOGIN = "/api/verifyLogin"` constant to `Endpoints.java` and verify constant availability

## 2. Screenplay Tasks & Models

- [ ] 2.1 Implement `VerifyLogin` Screenplay task in `com.transunion.automation.tasks.auth` supporting credential submission and missing parameters
- [ ] 2.2 Verify task handles `ContentType.URLENC` and integrates with `ExecuteUnsupportedMethod` for DELETE requests

## 3. Cucumber Features & Step Definitions

- [ ] 3.1 Create `src/test/resources/features/auth/login_verification.feature` in Spanish (`# language: es`) covering APIs 7, 8, 9, and 10
- [ ] 3.2 Implement `LoginStepDefinitions.java` in `com.transunion.automation.stepdefinitions` binding all Spanish login verification steps

## 4. Verification & Reporting

- [ ] 4.1 Verify Checkstyle passes with zero errors using `./gradlew checkstyleMain checkstyleTest`
- [ ] 4.2 Execute test suite with `./gradlew clean test aggregate` and verify all 4 login verification scenarios pass

# Tasks

## 1. Project & Build Configuration

- [ ] 1.1 Create `build.gradle`, `settings.gradle`, and `gradle.properties` targeting Java 21 with Serenity BDD, Cucumber 7, Serenity REST-Assured, Lombok, Datafaker, and JSON Schema validator, and verify dependencies resolve cleanly.
- [ ] 1.2 Configure environment configuration `src/test/resources/serenity.conf` and logging `src/test/resources/logback-test.xml`, and verify configuration loading.

## 2. Core Architecture & Design Patterns

- [ ] 2.1 Implement Strategy Pattern for authentication (`AuthenticationStrategy`, `BearerTokenAuthStrategy`, `ApiKeyAuthStrategy`, `NoAuthStrategy`) under `src/main/java/com/transunion/automation/interactions/auth/`.
- [ ] 2.2 Implement Builder Pattern DTOs (`UserRequestDto`, `UserResponseDto`) with Lombok `@Builder` and `@Jacksonized` under `src/main/java/com/transunion/automation/models/`.
- [ ] 2.3 Implement Factory Pattern (`UserDataFactory`) using Datafaker for dynamic and randomized payload generation under `src/main/java/com/transunion/automation/utils/data/`.
- [ ] 2.4 Implement Screenplay Tasks and Facades (`CreateUser`, `QueryUserById`, `UserOnboardingFacade`) under `src/main/java/com/transunion/automation/tasks/`.
- [ ] 2.5 Implement Screenplay Questions (`LastResponseStatusCode`, `ResponseSchemaMatches`) for status code and contract validations under `src/main/java/com/transunion/automation/questions/`.

## 3. BDD Features, Step Definitions & Verification

- [ ] 3.1 Create JSON Schema contract definition `src/test/resources/schemas/user_schema.json`.
- [ ] 3.2 Create Cucumber BDD feature file `src/test/resources/features/user/create_user.feature` covering success, validation, and contract check scenarios.
- [ ] 3.3 Implement Step Definitions (`UserStepDefinitions`, `CommonHooks`) under `src/test/java/com/transunion/automation/stepdefinitions/`.
- [ ] 3.4 Create Serenity Cucumber test runner `src/test/java/com/transunion/automation/runners/ApiTestSuiteRunner.java` and verify execution with `./gradlew test aggregate`.

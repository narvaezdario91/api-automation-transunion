# Tasks

## 1. Feature Files Localization to Spanish

- [x] 1.1 Localize `products_catalog.feature` to Spanish with `# language: es` and Spanish Gherkin keywords
- [x] 1.2 Localize `brands_catalog.feature` to Spanish with `# language: es` and Spanish Gherkin keywords
- [x] 1.3 Localize `search_product.feature` to Spanish with `# language: es` and Spanish Gherkin keywords

## 2. Step Definitions & Cleanup

- [x] 2.1 Update `CatalogStepDefinitions.java` with `@Dado`, `@Cuando`, `@Entonces`, `@Y` annotations matching Spanish Gherkin steps
- [x] 2.2 Delete legacy `create_user.feature`, `UserStepDefinitions.java`, `tasks/user/`, `tasks/facades/`, `UserDataFactory.java`, `UserRequestDto.java`, `UserResponseDto.java`, `UserResponseBody.java`, and `user_schema.json`
- [x] 2.3 Clean obsolete constants in `Endpoints.java` and `serenity.conf`

## 3. Verification & Quality Gate

- [x] 3.1 Verify Checkstyle passes with zero errors using `./gradlew checkstyleMain checkstyleTest`
- [x] 3.2 Execute test suite using `./gradlew clean test aggregate` and verify all localized scenarios pass

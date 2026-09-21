# Design

## Context

See [proposal.md](proposal.md) for motivation.
Following the initial domain architecture refactor, repetitive HTTP mechanics (deserialization with `ObjectMapper`, `formParam` boilerplate, `relaxedHTTPSValidation`, and unsupported method handling) remain distributed across domain tasks, questions, and step definitions.

## Goals / Non-Goals

**Goals:**
- Centralize JSON DTO response parsing into a single generic Question in `core.questions`.
- Relocate `ExecuteUnsupportedMethod` to `core.tasks` to eliminate cross-domain task dependencies.
- Provide a reusable form-encoded interaction in `core.tasks` (`PostForm`, `PutForm`, `DeleteWithForm`) to eliminate RestAssured boilerplate in domain tasks.
- Centralize unsupported HTTP method step definitions in `CommonApiStepDefinitions`.
- Standardize all assertions in `CommonApiStepDefinitions` to use pure Screenplay matchers (`Hamcrest`).
- Maintain 100% passing rate across all 16 Cucumber scenarios without modifying `.feature` files.

**Non-Goals:**
- Modifying Spanish Gherkin feature files or Cucumber scenario texts.
- Modifying domain DTO models or API payload contracts.

## Decisions

### 1. Generic Response Parsing via `LastResponseBody<T>`
- **Choice**: Implement `LastResponseBody<T> implements Question<T>` in `com.transunion.automation.core.questions`:
  ```java
  public static <T> LastResponseBody<T> of(Class<T> targetClass) { ... }
  ```
- **Domain Questions**: Refactor `ProductsListResponse`, `BrandsListResponse`, and `UserDetailResponse` to delegate to `LastResponseBody.of(...)`, keeping domain-specific factory methods (`ProductsListResponse.received()`) intact for readability.
- **Alternatives Considered**: Using `SerenityRest.lastResponse().as(targetClass)` directly. While RestAssured supports this, a Screenplay `Question<T>` provides clean integration with `Actor.asksFor(...)` and Serenity step reporting.

### 2. Move `ExecuteUnsupportedMethod` to `core.tasks`
- **Choice**: Move `ExecuteUnsupportedMethod` from `com.transunion.automation.domain.catalog.tasks` to `com.transunion.automation.core.tasks.ExecuteUnsupportedMethod`.
- **Rationale**: Any endpoint can receive an unsupported method in negative testing; it is a generic HTTP protocol interaction.
- **Alternatives Considered**: Duplicating task in `domain.auth` (rejected: anti-pattern).

### 3. Centralized Form Request Tasks in `core.tasks`
- **Choice**: Create reusable tasks in `com.transunion.automation.core.tasks`:
  - `PostForm.to(endpoint, formParams)`
  - `PutForm.to(endpoint, formParams)`
  - `DeleteWithForm.from(endpoint, formParams)`
  Configured with `ContentType.URLENC.withCharset("UTF-8")` and `relaxedHTTPSValidation()`.
- **Rationale**: Tasks `CreateAccount`, `UpdateAccount`, `DeleteAccount`, `VerifyLogin`, and `SearchProduct` become concise builders that convert their models to parameter maps and delegate to these core tasks.

### 4. Unify Unsupported Method Step Definitions
- **Choice**: Move the 3 unsupported method step definition methods from `CatalogStepDefinitions` and `LoginStepDefinitions` to `CommonApiStepDefinitions`. Map the endpoint Spanish descriptions (`"lista de productos"`, `"lista de marcas"`, `"verificación de login"`) cleanly to their corresponding `Endpoints` constants.
- **Rationale**: Eliminates duplicate glue code in separate step definition files.

### 5. Standardize Screenplay Matchers
- **Choice**: In `CommonApiStepDefinitions.elCodigoDeRespuestaEnElCuerpoNoDebeSer`, replace direct `Assertions.assertThat` with:
  ```java
  OnStage.theActorInTheSpotlight().should(seeThat(ApiResponseCode.fromBody(), not(equalTo(unexpectedCode))));
  ```
- **Rationale**: Enforces consistent Screenplay assertions across the entire test suite.

## Risks / Trade-offs

- **[Risk]** Step definition conflict or unresolved Cucumber regex when unifying steps.  
  → **Mitigation**: Keep the exact 3 `@Cuando` expressions in `CommonApiStepDefinitions` delegating to a shared private helper, ensuring zero ambiguity.
- **[Risk]** Serialization/deserialization mismatch with Jackson in generic `LastResponseBody`.  
  → **Mitigation**: Reuse existing `ObjectMapper` configuration and execute `./gradlew test` on all scenarios.
- **[Risk]** Checkstyle regression.  
  → **Mitigation**: Run `./gradlew checkstyleMain checkstyleTest` to enforce zero violations.

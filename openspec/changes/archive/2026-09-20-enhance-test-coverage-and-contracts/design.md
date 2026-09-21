# Design: Enhance Test Coverage and Contracts

## Context

The framework is built on Java 21, Serenity BDD, Screenplay pattern, and Cucumber with feature files written in Spanish. Test execution targets the public Automation Exercise API. Current coverage covers the 14 official endpoints, but omits important negative variations, lacks schema validation on standard error responses, and has a tag expression mismatch in the precondition hook for API-07.

## Goals / Non-Goals

**Goals:**
- Provide robust coverage for missing negative scenarios and boundary queries (account creation missing required fields, account update invalid/non-existent user, account deletion missing password, login verification missing password, product search non-existent term).
- Standardize error/mutation response contract validation with `schemas/common/api_response_schema.json`.
- Fix Cucumber hook targeting in `CommonHooks.java` and `login_verification.feature` to guarantee user existence before login verification.

**Non-Goals:**
- Adding unit tests or JaCoCo plugin to Gradle (deferred to a separate architecture change).
- Modifying core Screenplay execution abstractions or transport layer classes.

## Decisions

### Decision 1: Centralized Generic API Response JSON Schema
- **Choice**: Create `src/test/resources/schemas/common/api_response_schema.json` specifying `type: object`, `required: ["responseCode", "message"]`, `responseCode: integer`, `message: string`.
- **Rationale**: Automation Exercise responds to mutations and negative checks with a uniform `{ "responseCode": int, "message": "string" }` JSON envelope. Validating against a schema ensures contract stability across API-02, 04, 06, 08, 09, 10, 11, 12, and 13.
- **Alternative Considered**: Asserting only on individual string values with `ApiResponseMessage.returned()`. Rejected because it allows silent schema changes (e.g., field renaming) to pass unnoticed.

### Decision 2: Hook Tag Expression & Semantic Tagging
- **Choice**: Update `CommonHooks.java` to `@Before(value = "@requires_user or @api:API-07", order = 1)` and add `@requires_user` directly to the scenario in `login_verification.feature`.
- **Rationale**: Dual-defense ensures that whether filtering by domain (`@auth`), smoke (`@smoke`), or endpoint (`@api:API-07`), the precondition hook executes and guarantees the existence of the default user account.
- **Alternative Considered**: Only fixing the string in `CommonHooks.java`. Adding `@requires_user` to the Gherkin scenario makes the requirement explicit in Living Documentation.

### Decision 3: Parameterized Form Tasks for Negative Variations
- **Choice**: Reuse existing `PostForm`, `PutForm`, and `DeleteWithForm` tasks to submit payloads with missing or invalid keys directly or via overloaded task factory methods.
- **Rationale**: Avoids code duplication in the Screenplay task layer and maintains clean, readable step definitions.

## Risks / Trade-offs

- **[Risk] Automation Exercise HTTP Status Code Inconsistency**: Automation Exercise frequently returns HTTP 200 at the transport layer while embedding `{ "responseCode": 400, "message": "..." }` in the response body.
  - **Mitigation**: Scenarios must continue to assert against `ApiResponseCode.fromBody()` as well as `LastResponseStatusCode.is()` according to each endpoint's actual behavior.
- **[Risk] Search Product Empty List Contract**: When no products match, the response is `{ "responseCode": 200, "products": [] }`.
  - **Mitigation**: Validate both `products_list_schema.json` (which allows empty arrays) and a specific Screenplay question asserting that the product list is empty.

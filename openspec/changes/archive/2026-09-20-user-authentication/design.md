# Design: Automation Exercise User Authentication & Login Verification

## Context
See `proposal.md` for background and motivation. This design covers Phase 2 (APIs 7 to 10 of Automation Exercise), verifying positive and negative login checks and unsupported methods using Screenplay and Serenity BDD.

## Goals / Non-Goals

**Goals:**
- Implement `VerifyLogin` Screenplay task supporting valid, invalid, and missing parameter requests.
- Reutilize `ExecuteUnsupportedMethod.on(Endpoints.VERIFY_LOGIN, "DELETE")` for API 9.
- Reutilize `ApiResponseCode` and `ApiResponseMessage` for clean, consistent assertions.
- Provide clear Gherkin feature files in Spanish (`# language: es`) under `src/test/resources/features/auth/login_verification.feature`.

**Non-Goals:**
- Implement Account Lifecycle CRUD (APIs 11-14); that belongs to Phase 3.

## Decisions

### Decision 1: Fluent VerifyLogin Task
- **Choice**: Implement `VerifyLogin` task with fluent builder methods:
  - `VerifyLogin.withCredentials(email, password)`
  - `VerifyLogin.withoutEmail(password)`
  - `VerifyLogin.withoutPassword(email)`
- **Rationale**: Clean, readable, and handles `application/x-www-form-urlencoded` format seamlessly.

### Decision 2: Reuse of Common Questions and Unsupported Method Task
- **Choice**: Reuse `ExecuteUnsupportedMethod.on(Endpoints.VERIFY_LOGIN, "DELETE")`, `ApiResponseMessage`, and `ApiResponseCode`.
- **Rationale**: Zero code duplication across the framework.

## Risks / Trade-offs

- **[Risk] Pre-existing registered user credentials for API 7**:
  - *Mitigation*: Automation Exercise has known static test users or we can use a standard registered test account; if dynamic registration is desired, Phase 3 (Account Creation) will provide end-to-end integration.

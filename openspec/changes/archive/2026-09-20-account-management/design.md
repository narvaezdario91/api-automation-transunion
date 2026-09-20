# Design

## Context

See `proposal.md` for background and motivation. The project is built using Serenity BDD with the Screenplay Pattern, RestAssured, and Cucumber in Spanish. Phases 1 and 2 established catalog querying, schema validation, and login verification. Phase 3 introduces full account lifecycle automation (APIs 11-14) and test data fixture management.

## Goals / Non-Goals

**Goals:**
- Implement Screenplay tasks for `CreateAccount`, `DeleteAccount`, `UpdateAccount`, and `GetUserDetail` conforming to Automation Exercise API contracts.
- Model account registration and update payloads with builder/factory patterns (`UserData`, `AccountDataFactory`) to manage form-urlencoded payloads cleanly.
- Implement `user_detail_schema.json` to validate `GET /api/getUserDetailByEmail` structure.
- Provide a self-healing precondition mechanism (`EnsureUserExists` task and `@Before("@requires_user")` hook in `CommonHooks.java`) to ensure tests needing registered accounts run independently.
- Maintain 100% Checkstyle compliance and clear Serenity BDD reports.

**Non-Goals:**
- UI automation of registration forms.
- Mocking external services (tests run against the real API target).

## Decisions

### Decision 1: Form-Urlencoded Parameter Transmission
- **Choice**: Use RestAssured `.contentType(ContentType.URLENC)` and `.formParams(...)` for `createAccount`, `updateAccount`, and `deleteAccount`.
- **Rationale**: Automation Exercise backend expects form-encoded body parameters rather than JSON for account mutation endpoints.
- **Alternative considered**: Sending raw JSON body (rejected because the backend returns 400 parameter missing errors).

### Decision 2: Dedicated Account Data Factory / Model
- **Choice**: Introduce `AccountData` model and `AccountDataFactory` to provide standard default test account data (name, email, password, address, city, etc.) with override capabilities.
- **Rationale**: Avoids verbose boilerplate in step definitions and keeps Gherkin scenarios clean.

### Decision 3: Autonomous Test Fixture via Hook and Task
- **Choice**: Create `EnsureUserExists` Screenplay task that checks if the default test user exists (via `POST /api/verifyLogin` or `GET /api/getUserDetailByEmail`); if not found, automatically calls `CreateAccount`.
- **Rationale**: Solves test flakiness and eliminates manual user setup in public test environments without adding boilerplate to feature files.

## Risks / Trade-offs

- **[Risk] Multiple test runs deleting shared test users** → **Mitigation**: Lifecycle deletion scenarios (API 12) will create their own dedicated ephemeral user (e.g. `ephemeral_<timestamp>@test.com`) before deleting it, preventing disruption to shared test users.
- **[Risk] Flakiness on user detail retrieval if user is deleted** → **Mitigation**: The `GetUserDetail` scenario uses `EnsureUserExists` as a precondition.

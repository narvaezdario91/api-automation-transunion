# Proposal

## Why

Automation Exercise provides user account lifecycle management APIs (APIs 11 through 14) for creating accounts, deleting accounts, updating profile information, and retrieving user details by email. Automating these endpoints provides complete coverage of account CRUD operations and enables self-healing test data fixtures for scenarios requiring pre-existing accounts (such as login verification).

## What Changes

- Add endpoint constants in `Endpoints.java` (`/api/createAccount`, `/api/deleteAccount`, `/api/updateAccount`, `/api/getUserDetailByEmail`).
- Implement Screenplay tasks for account lifecycle operations:
  - `CreateAccount` (POST `/api/createAccount` with `application/x-www-form-urlencoded` body)
  - `DeleteAccount` (DELETE `/api/deleteAccount` with URL-encoded credentials)
  - `UpdateAccount` (PUT `/api/updateAccount` with URL-encoded profile fields)
  - `GetUserDetail` (GET `/api/getUserDetailByEmail` with query parameter)
  - `EnsureUserExists` task / hook support for autonomous test data management.
- Create JSON contract schemas for `user_detail_schema.json` and account response formats.
- Create Gherkin feature file `src/test/resources/features/account/account_management.feature` in Spanish (`# language: es`) covering APIs 11 to 14.
- Create `AccountStepDefinitions.java` binding all Spanish steps using Screenplay assertions.
- Verify Checkstyle compliance and test execution with Serenity BDD reporting.

## Capabilities

### New Capabilities
- `account-management`: Covers API test automation for Automation Exercise account lifecycle operations (create, delete, update, and get user details) along with test fixture pre-condition support.

### Modified Capabilities
<!-- None -->

## Impact

- Adds new tasks under `com.transunion.automation.tasks.account`.
- Adds new models/builders under `com.transunion.automation.models.account` for user registration and profile update data.
- Adds new step definitions under `com.transunion.automation.stepdefinitions.AccountStepDefinitions`.
- Extends test suites to include `@account` scenarios alongside `@catalog` and `@auth`.

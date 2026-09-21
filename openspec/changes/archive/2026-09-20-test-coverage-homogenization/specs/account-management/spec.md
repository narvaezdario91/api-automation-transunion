# Spec Delta

## ADDED Requirements

### Requirement: Negative Coverage for Account Creation
The test framework SHALL verify that attempting to create a user account with an email that already exists returns a 400 error response indicating the account already exists, and that attempting to create an account with missing required fields also returns a 400 error response.

#### Scenario: Attempt to create a duplicate user account
- **WHEN** an actor sends a POST request to `/api/createAccount` with an email that is already registered
- **THEN** the internal responseCode SHALL be 400
- **AND** the response message SHALL indicate the account already exists

#### Scenario: Attempt to create a user account without required fields
- **WHEN** an actor sends a POST request to `/api/createAccount` without providing all required registration fields
- **THEN** the internal responseCode SHALL be 400

### Requirement: Negative Coverage for User Detail Retrieval
The test framework SHALL verify that querying `GET /api/getUserDetailByEmail` with an email that does not correspond to any registered account returns a 404 response indicating the user was not found.

#### Scenario: Query user details for a non-existent email
- **WHEN** an actor sends a GET request to `/api/getUserDetailByEmail` with an email that is not registered
- **THEN** the internal responseCode SHALL be 404
- **AND** the response message SHALL indicate the user was not found

### Requirement: Negative Coverage for Account Deletion with Invalid Credentials
The test framework SHALL verify that sending a DELETE request to `/api/deleteAccount` with invalid or unregistered email and password credentials returns an error response.

#### Scenario: Attempt to delete an account with invalid credentials
- **WHEN** an actor sends a DELETE request to `/api/deleteAccount` with an email and password that do not match any registered account
- **THEN** the internal responseCode SHALL not be 200
- **AND** the response message SHALL not be \"Account deleted!\"

### Requirement: Immutable Builder-based Account Data in Step Definitions
The test framework SHALL construct all `AccountData` instances exclusively via the Lombok builder, including when overriding individual fields such as name, without calling post-construction setters.

#### Scenario: Build AccountData with overridden name field via factory
- **WHEN** a step definition requires an AccountData with a custom name for a specific email and password
- **THEN** the AccountData SHALL be created using the builder pattern through an `AccountDataFactory` method that accepts the name as a parameter
- **AND** no setter SHALL be invoked on the AccountData object after construction

# Design: Automation Exercise Products & Brands Catalog API Automation

## Context
See `proposal.md` for background and motivation. The existing framework is structured around the Screenplay pattern with Serenity BDD and Cucumber, currently with base configuration in `serenity.conf` and REST interactions. This design defines a modular, non-overengineered solution for catalog and search operations (APIs 1 to 6).

## Goals / Non-Goals

**Goals:**
- Implement modular Screenplay Tasks and Questions for products catalog, brands catalog, and product search.
- Create reusable components (`ExecuteUnsupportedMethod`, `AutomationExerciseResponseDto`) to eliminate code duplication across unsupported method scenarios (APIs 2, 4) and error validation.
- Define JSON schema contract files for `productsList` and `brandsList` responses.
- Implement clear, maintainable Cucumber feature files with step definitions for APIs 1-6.

**Non-Goals:**
- Automate User Authentication or Account Management (APIs 7 to 14) in this change; those belong to subsequent phases.
- Add third-party heavy dependencies or unnecessary abstraction layers.

## Decisions

### Decision 1: Reusable Task for Unsupported HTTP Methods
- **Choice**: Implement a reusable Screenplay task `ExecuteUnsupportedMethod.on(endpoint, httpMethod)` to handle 405 Method Not Allowed validation cleanly across endpoints.
- **Rationale**: Eliminates repetitive task classes for disallowed HTTP methods while keeping intent crystal clear.

### Decision 2: Standard DTO Models & Separation of Concerns
- **Choice**: Follow standard Software Engineering naming conventions using DTOs: `ProductDto`, `BrandDto`, `ProductsListResponseDto`, `BrandsListResponseDto`, and `ApiResponseDto`.
- **Rationale**: Maintains consistency with existing project models (`UserRequestDto`, `UserResponseDto`), guarantees type safety, and is immediately understandable by any software engineer.

### Decision 3: Fluent Interaction for Search Operations
- **Choice**: Implement `SearchProduct` task with fluent API `SearchProduct.withTerm(term)` and `SearchProduct.withoutParameters()`.
- **Rationale**: Clear, readable, and directly maps to the test intent in Cucumber step definitions.

## Risks / Trade-offs

- **[Risk] Endpoint returns HTTP 200 with responseCode inside JSON vs protocol HTTP status**:
  - *Mitigation*: Our custom assertions and step definitions will inspect both the HTTP status code and the `responseCode` body attribute if the mock server returns status 200 with internal error code.
- **[Risk] Automation Exercise API availability / rate limiting**:
  - *Mitigation*: Configure standard connection timeouts and logging in `serenity.conf`.

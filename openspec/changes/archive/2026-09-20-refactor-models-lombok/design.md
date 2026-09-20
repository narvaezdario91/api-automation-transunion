# Design

## Context

See `proposal.md` for background. The project compiles with Java 21, Gradle, Serenity BDD 4.2.16, and Lombok 1.18.32. Catalog DTOs already use `@Getter`, `@Builder`, `@Jacksonized`, `@ToString`, while `AccountData` was authored with manual getters and setters.

## Goals / Non-Goals

**Goals:**
- Eliminate boilerplate code in `AccountData` by using Lombok annotations.
- Refactor `AccountDataFactory` to use fluent builder pattern syntax (`AccountData.builder()...build()`).
- Introduce typed response DTOs (`UserDetailResponseDto` and `UserDto`) with Lombok for complete parity with Catalog DTOs.
- Clean up empty orphan directories (`src/main/java/com/transunion/automation/models/request/` and `src/main/java/com/transunion/automation/utils/data/`).
- Preserve 100% test pass rate and zero Checkstyle warnings.

**Non-Goals:**
- Changing external API behavior or cucumber feature definitions.
- Modifying Screenplay task interfaces or interactions.

## Decisions

### Decision 1: Lombok Annotations for AccountData
- **Choice**: Apply `@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`, and `@ToString` to `AccountData`.
- **Rationale**: Provides immutability via `@Builder` where needed while preserving `@Setter` and `@NoArgsConstructor` for flexible field overrides during test steps (e.g. updating a single attribute like user name).
- **Alternative considered**: Java 21 `record` (rejected for `AccountData` because 17 required parameters make constructor calls unwieldy, and records are strictly immutable preventing partial mutation in update steps).

### Decision 2: Retain `toFormParamMap()` Utility
- **Choice**: Keep `toFormParamMap()` on `AccountData`.
- **Rationale**: Encapsulates the conversion of Java camelCase attributes to Automation Exercise form-urlencoded snake_case parameters (`birth_date`, `mobile_number`, etc.), isolating API mapping logic from test steps.

### Decision 3: Parity with Jacksonized DTOs for User Detail Response
- **Choice**: Introduce `UserDetailResponseDto` and `UserDto` decorated with `@Getter`, `@Builder`, `@Jacksonized`, `@ToString`, and `@JsonIgnoreProperties(ignoreUnknown = true)`.
- **Rationale**: Aligns account response handling with the pattern established in `ProductsListResponseDto` and `BrandsListResponseDto`.

### Decision 4: Directory Cleanup
- **Choice**: Remove empty directories `models/request` and `utils/data`.
- **Rationale**: Eliminates dead structure, reducing cognitive load when navigating the project tree.

## Risks / Trade-offs

- **[Risk] Checkstyle complaining about Lombok generated code** → **Mitigation**: Checkstyle 10.17.0 checks source files before compilation; existing Lombok classes (`BrandDto`, `ApiKeyAuthStrategy`) pass with 0 warnings.
- **[Risk] Regression in existing tests referencing setters or constructors** → **Mitigation**: `@NoArgsConstructor` and `@Setter` preserve backwards compatibility for any existing code.

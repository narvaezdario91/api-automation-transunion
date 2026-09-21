# Proposal

## Why

The project currently exhibits architectural inconsistency across its model layer: catalog models (`BrandDto`, `ProductDto`, etc.) leverage Lombok annotations (`@Getter`, `@Builder`, `@Jacksonized`), whereas `AccountData.java` contains over 140 lines of manual getters and setters. Refactoring all models and test data factories to use standardized Lombok patterns improves maintainability, eliminates boilerplate, and ensures uniform design principles across the codebase.

## What Changes

- Refactor `AccountData.java` to use Lombok annotations (`@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@ToString`), reducing file size by ~65% while keeping form-parameter mapping intact.
- Refactor `AccountDataFactory.java` to utilize fluent `AccountData.builder()` instantiation across all factory methods (`defaultUser`, `withEmailAndPassword`, `dynamicUser`).
- Introduce typed response DTOs `UserDetailResponseDto.java` and `UserDto.java` in `com.transunion.automation.models.account` with Lombok annotations for architectural consistency with the catalog domain.
- Clean up unused empty directories (`src/main/java/com/transunion/automation/models/request/` and `src/main/java/com/transunion/automation/utils/data/`).
- Validate that all 16 existing automated scenarios and Checkstyle quality gates pass without regressions.

## Capabilities

### New Capabilities
<!-- None -->

### Modified Capabilities
- `account-management`: Adds typed response modeling and standardized builder-based test data representation to account management.

## Impact

- Affects `com.transunion.automation.models.account` package.
- No changes to Cucumber feature files, step definitions, endpoints, or REST API contracts.

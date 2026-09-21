# Proposal: Automation Exercise Products & Brands Catalog API Automation

## Why
Extend our API test automation framework to cover the Products and Brands Catalog APIs (APIs 1 to 6) from Automation Exercise (`https://automationexercise.com/api_list`). This provides structured, BDD-driven contract and functional validation for catalog query endpoints, search operations, and unsupported HTTP method error handling using the Screenplay Pattern.

## What Changes
- Introduce end-to-end BDD tests for Automation Exercise catalog endpoints:
  - `GET /api/productsList` (API 1: list all products, 200 OK + JSON Schema validation).
  - `POST /api/productsList` (API 2: unsupported method handling, 405 Method Not Allowed).
  - `GET /api/brandsList` (API 3: list all brands, 200 OK + JSON Schema validation).
  - `PUT /api/brandsList` (API 4: unsupported method handling, 405 Method Not Allowed).
  - `POST /api/searchProduct` (API 5: search product with valid term, 200 OK + filtered product matching).
  - `POST /api/searchProduct` (API 6: search product without parameter, 400 Bad Request error verification).
- Add reusable Screenplay Tasks, Questions, DTO models, JSON Schemas, and Cucumber feature files for the catalog domain.
- Configure `serenity.conf` with the Automation Exercise environment endpoint.

## Capabilities

### New Capabilities
- `products-catalog`: Provides automated API testing coverage for Automation Exercise products and brands catalog endpoints, including search filters, JSON schema validation, and unsupported HTTP method handling.

### Modified Capabilities
<!-- No requirement changes to existing capabilities -->

## Impact
- **Codebase**: New models under `com.transunion.automation.models`, Screenplay tasks under `tasks.catalog`, constants in `Endpoints.java`, questions under `questions`, step definitions in `stepdefinitions.catalog`, and schemas in `src/test/resources/schemas/catalog/`.
- **Configuration**: `serenity.conf` updated to include `automationexercise` environment profile.
- **Dependencies**: Uses existing Serenity BDD, RestAssured, Jackson, and AssertJ dependencies without adding external third-party overhead.

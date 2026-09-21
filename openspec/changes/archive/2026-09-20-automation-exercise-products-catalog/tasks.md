# Tasks

## 1. Environment & Constants Setup

- [x] 1.1 Add Automation Exercise base URL and environment profile to `serenity.conf` and verify configuration loads correctly
- [x] 1.2 Define catalog endpoints (`/api/productsList`, `/api/brandsList`, `/api/searchProduct`) in `Endpoints.java` and verify constants are accessible

## 2. DTO Models & Contract Schemas

- [x] 2.1 Create standard DTO models `ProductDto`, `BrandDto`, `ProductsListResponseDto`, `BrandsListResponseDto`, and `ApiResponseDto` with Lombok annotations
- [x] 2.2 Add JSON contract schema files `products_list_schema.json` and `brands_list_schema.json` under `src/test/resources/schemas/catalog/` and verify schema structure validity

## 3. Screenplay Tasks & Questions

- [x] 3.1 Implement reusable `ExecuteUnsupportedMethod` task for handling 405 Method Not Allowed calls across endpoints
- [x] 3.2 Implement `GetProductsList` and `GetBrandsList` Screenplay tasks for fetching catalog data
- [x] 3.3 Implement `SearchProduct` Screenplay task supporting search with term (`withTerm`) and search without parameters (`withoutParameters`)
- [x] 3.4 Implement reusable Questions for extracting catalog response details and validating response messages

## 4. Cucumber Features & Step Definitions

- [x] 4.1 Create `products_catalog.feature` covering API 1 (Get products + schema) and API 2 (POST unsupported method)
- [x] 4.2 Create `brands_catalog.feature` covering API 3 (Get brands + schema) and API 4 (PUT unsupported method)
- [x] 4.3 Create `search_product.feature` covering API 5 (Search with valid term) and API 6 (Search without parameter)
- [x] 4.4 Implement modular Step Definitions in `CatalogStepDefinitions.java` binding all Gherkin steps to Screenplay tasks and assertions

## 5. Verification & Reporting

- [x] 5.1 Execute the full catalog test suite with `./gradlew clean test` and verify all 6 API scenarios pass
- [x] 5.2 Verify Checkstyle and Sonar compliance with `./gradlew checkstyleMain checkstyleTest`
- [x] 5.3 Verify Serenity BDD test execution reports in `target/site/serenity/index.html`

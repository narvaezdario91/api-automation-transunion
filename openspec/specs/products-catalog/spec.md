# products-catalog Specification

## Purpose
Provides automated API testing capabilities for the Automation Exercise products and brands catalog endpoints, verifying data contracts, search operations, and unsupported HTTP method responses.

## Requirements

### Requirement: Products Catalog Query and Schema Validation
The test framework SHALL allow an actor to request the complete list of products from `GET /api/productsList` and validate both the response status code and the JSON schema structure.

#### Scenario: Retrieve all products successfully
- **WHEN** an actor sends a GET request to `/api/productsList`
- **THEN** the response status code SHALL be 200
- **AND** the response body SHALL match the `products_list_schema.json` contract definition

### Requirement: Unsupported HTTP Method Handling for Products Catalog
The test framework SHALL verify that invoking unsupported HTTP methods on the products catalog endpoint returns appropriate HTTP 405 error responses and descriptive messages.

#### Scenario: Send POST request to products list endpoint
- **WHEN** an actor sends a POST request to `/api/productsList`
- **THEN** the response status code SHALL be 405 or the response body SHALL indicate responseCode 405
- **AND** the response message SHALL contain "This request method is not supported."

### Requirement: Brands Catalog Query and Schema Validation
The test framework SHALL allow an actor to query the brands list from `GET /api/brandsList` and validate the response structure against the brands JSON schema.

#### Scenario: Retrieve all brands successfully
- **WHEN** an actor sends a GET request to `/api/brandsList`
- **THEN** the response status code SHALL be 200
- **AND** the response body SHALL match the `brands_list_schema.json` contract definition

### Requirement: Unsupported HTTP Method Handling for Brands Catalog
The test framework SHALL verify that invoking unsupported HTTP methods on the brands list endpoint returns appropriate HTTP 405 error responses and messages.

#### Scenario: Send PUT request to brands list endpoint
- **WHEN** an actor sends a PUT request to `/api/brandsList`
- **THEN** the response status code SHALL be 405 or the response body SHALL indicate responseCode 405
- **AND** the response message SHALL contain "This request method is not supported."

### Requirement: Product Search with Valid Query Parameter
The test framework SHALL support searching for products using a search term via `POST /api/searchProduct` and verifying that returned products match the search criteria.

#### Scenario: Search products with valid search term
- **WHEN** an actor sends a POST request to `/api/searchProduct` with parameter `search_product` set to "top"
- **THEN** the response status code SHALL be 200
- **AND** the response SHALL contain a list of products related to "top"

### Requirement: Product Search Missing Parameter Handling
The test framework SHALL verify that attempting to search products without providing the mandatory `search_product` parameter returns a 400 Bad Request response with a descriptive error message.

#### Scenario: Search products without search_product parameter
- **WHEN** an actor sends a POST request to `/api/searchProduct` without the `search_product` parameter
- **THEN** the response status code SHALL be 400 or the response body SHALL indicate responseCode 400
- **AND** the response message SHALL contain "Bad request, search_product parameter is missing in POST request."

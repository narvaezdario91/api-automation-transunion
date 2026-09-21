# Spec Delta: products-catalog

## ADDED Requirements

### Requirement: Product Search Non-Existent Query Handling
The test framework SHALL verify that searching for a product using a non-matching keyword returns an empty product list with status code 200, matching the `products_list_schema.json` contract.

#### Scenario: Search products with non-existent keyword
- **WHEN** an actor sends a POST request to `/api/searchProduct` with parameter `search_product` set to an unmatchable term
- **THEN** the response status code SHALL be 200
- **AND** the internal responseCode SHALL be 200
- **AND** the response body SHALL match the `products_list_schema.json` contract definition
- **AND** the products list in the response SHALL be empty

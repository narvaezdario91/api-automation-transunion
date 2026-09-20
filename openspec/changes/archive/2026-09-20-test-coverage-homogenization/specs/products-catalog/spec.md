# Spec Delta

## MODIFIED Requirements

### Requirement: Product Search with Valid Query Parameter
The test framework SHALL support searching for products using a search term via `POST /api/searchProduct`, verifying that returned products match the search criteria AND that the response body conforms to the `products_list_schema.json` JSON schema contract.

#### Scenario: Search products with valid search term
- **WHEN** an actor sends a POST request to `/api/searchProduct` with parameter `search_product` set to \"top\"
- **THEN** the response status code SHALL be 200
- **AND** the response body SHALL match the `products_list_schema.json` contract definition
- **AND** the response SHALL contain a list of products related to \"top\"

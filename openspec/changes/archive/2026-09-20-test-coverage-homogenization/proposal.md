# Proposal

## Why

El análisis de la suite de pruebas revela brechas de cobertura funcional, inconsistencias en la estrategia de aserción entre dominios y un caso de mutación post-builder que viola el principio de inmutabilidad adoptado con Lombok. Resolver estos problemas ahora garantiza homogeneidad y facilita la extensión futura del framework.

## What Changes

- **Escenarios negativos para `account_management`**: Añadir 4 nuevos escenarios que cubran creación de cuenta duplicada (400), campos requeridos faltantes (400), consulta de usuario inexistente (404) y eliminación con credenciales inválidas (401/403).
- **Validación de contrato JSON en `search_product`**: Añadir aserción de JSON Schema en el escenario de búsqueda exitosa, reutilizando `products_list_schema.json`.
- **Validación de contrato JSON en `login_verification`**: Crear `login_response_schema.json` y añadir la aserción de contrato en el escenario happy path (@api7).
- **Refactor de `AccountStepDefinitions`**: Eliminar la llamada `setName()` post-builder; construir `AccountData` directamente con el campo `name` en el builder a través de un nuevo método de factory.
- **Estandarización de estrategia de aserción**: Homogeneizar el uso de `actor.should(seeThat(...))` con `assertThat` de AssertJ para validaciones complejas de DTOs, eliminando el uso de Hamcrest raw donde AssertJ ya se usa en el mismo bloque.

## Capabilities

### New Capabilities

_(ninguna)_

### Modified Capabilities

- `account-management`: Añadir requisitos de cobertura negativa (cuenta duplicada, campos faltantes, usuario no encontrado, credenciales inválidas para eliminación) y requisito de builder inmutable en step definitions.
- `products-catalog`: Añadir requisito de validación de contrato JSON Schema en el escenario de búsqueda de productos.
- `user-authentication`: Añadir requisito de validación de contrato JSON Schema en el escenario de verificación de login exitoso.

## Impact

- `src/test/resources/features/account/account_management.feature`: 4 nuevos escenarios negativos.
- `src/test/resources/features/catalog/search_product.feature`: 1 aserción de schema adicional en escenario de búsqueda exitosa.
- `src/test/resources/features/auth/login_verification.feature`: 1 aserción de schema adicional en escenario @api7.
- `src/test/resources/schemas/auth/login_response_schema.json`: Nuevo archivo de esquema JSON.
- `src/main/java/com/transunion/automation/models/account/AccountDataFactory.java`: Nuevo método `withEmailPasswordAndName(...)`.
- `src/test/java/com/transunion/automation/stepdefinitions/AccountStepDefinitions.java`: Refactor del step de actualización para usar builder.
- Sin cambios a la API pública del framework ni a dependencias de terceros.

# Design

## Context

El framework usa Serenity BDD + Screenplay + Cucumber con feature files en español. Los dominios (catalog, auth, account) ya tienen su capa de Tasks y Questions homogénea. Los gaps identificados son de tres tipos: cobertura funcional negativa faltante en account, ausencia de validación de contrato en search y login, y un caso de mutación post-builder que viola la inmutabilidad de `AccountData`.

## Goals / Non-Goals

**Goals:**
- Añadir escenarios negativos en `account_management.feature` para crear/consultar/eliminar con datos inválidos.
- Añadir validación de JSON Schema en `search_product.feature` (reutilizando `products_list_schema.json`) y en `login_verification.feature` (nuevo schema `login_response_schema.json`).
- Refactorizar `AccountStepDefinitions` para eliminar `setName()` post-builder.
- Añadir método `withEmailPasswordAndName()` en `AccountDataFactory`.

**Non-Goals:**
- Cambiar la estrategia de aserción global (`seeThat` vs AssertJ). Esta decisión se documentó como G5 pero su impacto es cosmético en el reporte y se puede abordar en un cambio dedicado.
- Añadir nuevas Questions o Tasks que no sean necesarias para los escenarios propuestos.
- Cambiar el esquema de tags o la estructura de los runners.

## Decisions

### 1. Reusar `products_list_schema.json` para la validación de search
El endpoint `POST /api/searchProduct` devuelve exactamente la misma estructura que `GET /api/productsList` (un JSON con clave `"products"` que contiene un array de objetos producto). No se crea un nuevo schema: se reutiliza `schemas/catalog/products_list_schema.json` directamente en el paso de validación.

**Alternativa descartada**: Crear un `search_result_schema.json` independiente. Innecesario porque la estructura es idéntica y duplicaría la fuente de verdad del contrato.

### 2. Nuevo schema `login_response_schema.json`
La respuesta de `/api/verifyLogin` tiene una estructura diferente a la de catalog: `{ "responseCode": int, "message": string }`. Se crea `src/test/resources/schemas/auth/login_response_schema.json` para documentar y validar este contrato.

### 3. `AccountDataFactory.withEmailPasswordAndName()` como nueva sobrecarga
En lugar de que el step definition construya directamente con el builder, se delega al factory. Esto centraliza la lógica de construcción y mantiene el step definition limpio. El método acepta `(email, password, name)` y llama internamente al builder con todos los campos requeridos.

**Alternativa descartada**: Modificar `withEmailAndPassword` para devolver un builder expuesto. Rompe la encapsulación del factory pattern.

### 4. Escenarios negativos de account con datos dinámicos
Los escenarios de duplicado y credenciales inválidas deben usar datos dinámicos (timestamps) para evitar colisiones con el estado del servidor entre ejecuciones. Se reutiliza `AccountDataFactory.dynamicUser()` para el escenario de duplicado (crear el mismo usuario dos veces) y se introduce datos hardcoded claramente inválidos para el caso de credenciales incorrectas en delete.

## Risks / Trade-offs

- **Estabilidad del API externo**: Los códigos de respuesta para escenarios negativos de account (crear duplicado, delete con credenciales inválidas) dependen del comportamiento real de Automation Exercise. Si el servidor retorna códigos distintos a 400/401, los escenarios fallarán. Mitigación: ejecutar los tests antes del merge para confirmar los códigos reales.
- **Escenario de duplicado es stateful**: El test de cuenta duplicada crea un usuario en el paso Given y luego intenta crearlo de nuevo. Si el primer create falla, el segundo también fallará por razones distintas. Mitigación: usar `@requires_user` + la cuenta default si el servidor tiene estado persistente, o usar `dynamicUser` + `CreateAccount` en el Given.

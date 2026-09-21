# Tasks

## 1. Serenity Configuration Setup

- [x] 1.1 Configurar `tag.types` con valor `"api, capability, feature, issue"` y `issue.tracker.url` apuntando a `"https://automationexercise.com/api_list#collapse{0}"` en `src/test/resources/serenity.conf`, verificando que la sintaxis HOCON sea válida.

## 2. Catalog Features Traceability (API 1 a 6)

- [x] 2.1 Actualizar `src/test/resources/features/catalog/products_catalog.feature` incorporando tags `@api:API-01`, `@issue:1` para API 1 y `@api:API-02`, `@issue:2` para API 2, agregando los prefijos `[API-01] [GET /api/productsList]` y `[API-02] [POST /api/productsList]` en los títulos de escenario, y verificar sintaxis Gherkin.
- [x] 2.2 Actualizar `src/test/resources/features/catalog/brands_catalog.feature` incorporando tags `@api:API-03`, `@issue:3` para API 3 y `@api:API-04`, `@issue:4` para API 4, agregando los prefijos `[API-03] [GET /api/brandsList]` y `[API-04] [PUT /api/brandsList]` en los títulos de escenario, y verificar sintaxis Gherkin.
- [x] 2.3 Actualizar `src/test/resources/features/catalog/search_product.feature` incorporando tags `@api:API-05`, `@issue:5` para API 5 y `@api:API-06`, `@issue:6` para API 6, agregando los prefijos `[API-05] [POST /api/searchProduct]` y `[API-06] [POST /api/searchProduct]` en los títulos de escenario, y verificar sintaxis Gherkin.

## 3. Auth Features Traceability (API 7 a 10)

- [x] 3.1 Actualizar `src/test/resources/features/auth/login_verification.feature` migrando los tags de `@api7`..`@api10` a `@api:API-07`..`@api:API-10` con `@issue:7`..`@issue:10` y agregando los prefijos `[API-07] [POST /api/verifyLogin]`, `[API-08] [POST /api/verifyLogin]`, `[API-09] [DELETE /api/verifyLogin]`, `[API-10] [POST /api/verifyLogin]` en los títulos, y verificar sintaxis Gherkin.

## 4. Account Features Traceability (API 11 a 14)

- [x] 4.1 Actualizar `src/test/resources/features/account/account_management.feature` migrando los tags de `@api11`..`@api14` a `@api:API-11`..`@api:API-14` con `@issue:11`..`@issue:14` y agregando los prefijos correspondientes `[API-11] [POST /api/createAccount]`, `[API-12] [DELETE /api/deleteAccount]`, `[API-13] [PUT /api/updateAccount]` y `[API-14] [GET /api/getUserDetailByEmail]`, y verificar sintaxis Gherkin.

## 5. Verification and Serenity Report Validation

- [x] 5.1 Ejecutar `./gradlew.bat test aggregate` y validar que los 17 escenarios pasen al 100% sin errores de step definitions ni sintaxis.
- [x] 5.2 Inspeccionar los archivos JSON y HTML en `target/site/serenity` para confirmar que los tags de tipo `api` y los enlaces a `#collapse{0}` se generen correctamente en el reporte.

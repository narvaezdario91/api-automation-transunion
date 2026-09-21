# Proposal

## Why

El reporte actual de Serenity BDD carece de una vinculación clara y directa entre los escenarios automatizados y los 14 endpoints documentados en la lista oficial de APIs de Automation Exercise (`https://automationexercise.com/api_list`). Actualmente, los escenarios de catálogo carecen de etiquetas de API, los escenarios de autenticación y cuentas usan tags planos no tipados (`@api7`, `@api11`), y los títulos de los escenarios no identifican el endpoint ni el método HTTP evaluado. Esto dificulta la trazabilidad y la navegación de resultados para auditores, desarrolladores e ingenieros de calidad tanto en los reportes HTML locales como en las ejecuciones de CI/CD.

## What Changes

- **Configuración de Serenity BDD**: Configurar `serenity.tag.types = "api, capability, feature, issue"` en `serenity.conf` para habilitar una sección/filtro dedicado "API" en el dashboard de Serenity.
- **Integración con Issue Tracker / API List**: Configurar `serenity.issue.tracker.url = "https://automationexercise.com/api_list#collapse{0}"` en `serenity.conf` para permitir navegación directa con un solo clic al acordeón del endpoint oficial.
- **Etiquetado Uniforme de Escenarios (API 1 a 14)**:
  - Catálogo de productos y marcas (`API 1` a `API 6`): Agregar etiquetas tipadas `@api:API-01` a `@api:API-06` y enlaces `@issue:1` a `@issue:6`.
  - Autenticación (`API 7` a `API 10`): Migrar etiquetas `@api7`..`@api10` a `@api:API-07`..`@api:API-10` y agregar `@issue:7`..`@issue:10`.
  - Gestión de cuentas (`API 11` a `API 14`): Migrar etiquetas `@api11`..`@api14` a `@api:API-11`..`@api:API-14` y agregar `@issue:11`..`@issue:14`.
- **Estandarización de Nomenclatura de Escenarios**: Prefijar todos los escenarios Gherkin con el formato `[API-XX] [METHOD /endpoint] <descripción>` para garantizar visibilidad inmediata en reportes y consolas de CI/CD.
- **Actualización del Runner de Pruebas**: Ajustar las expresiones de tags en `ApiTestSuiteRunner.java` si es necesario para mantener compatibilidad total con ejecuciones filtradas.

## Capabilities

### New Capabilities
<!-- None -->

### Modified Capabilities
- `api-automation-core`: Agregar requerimiento de trazabilidad de escenarios API con tags tipados, hipervínculos a especificaciones externas y estandarización de títulos de escenarios.

## Impact

- **Archivos de Configuración**: `src/test/resources/serenity.conf`.
- **Archivos de Características (Gherkin)**:
  - `src/test/resources/features/catalog/products_catalog.feature`
  - `src/test/resources/features/catalog/brands_catalog.feature`
  - `src/test/resources/features/catalog/search_product.feature`
  - `src/test/resources/features/auth/login_verification.feature`
  - `src/test/resources/features/account/account_management.feature`
- **Runner**: `src/test/java/com/transunion/automation/runners/ApiTestSuiteRunner.java`.
- **Compatibilidad**: No rompe lógica de pruebas ni step definitions existentes; mejora exclusivamente metadatos, tags y reportes.

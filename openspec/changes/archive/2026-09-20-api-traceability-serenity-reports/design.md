# Design

## Context

Actualmente, el proyecto cuenta con 14 endpoints automatizados distribuidos en tres dominios (`catalog`, `auth`, `account`). Para detalles de motivación y problemas identificados, ver `proposal.md`. Este diseño técnico define la arquitectura de metadatos, tags y configuración de Serenity BDD para alcanzar trazabilidad completa hacia la documentación de `https://automationexercise.com/api_list`.

## Goals / Non-Goals

**Goals:**
- Configurar la taxonomía de tags de Serenity para crear una categoría de primer nivel "API" en los reportes HTML.
- Configurar el rastreador de issues de Serenity apuntando a los anclajes `#collapse{0}` de Automation Exercise para proveer enlaces directos en cada escenario.
- Estandarizar los 14 endpoints en los archivos `.feature` con tags estructurados `@api:API-XX`, `@issue:X` y títulos descriptivos con prefijo `[API-XX] [METHOD /endpoint]`.
- Mantener la organización actual por dominios (`catalog`, `auth`, `account`) sin romper step definitions ni tasks de Screenplay.

**Non-Goals:**
- Modificar la lógica interna de ejecución de pruebas (Tasks, Questions, DTOs o Helpers HTTP).
- Reorganizar la estructura de directorios de features (se preserva la separación por dominio de negocio).
- Reemplazar tags funcionales existentes (`@smoke`, `@regression`, `@contract`, `@negative`).

## Decisions

### Decisión 1: Etiquetas tipadas de Serenity (`@api:API-XX`)
* **Elección**: Usar `@api:API-01` a `@api:API-14` y declarar `serenity.tag.types = "api, capability, feature, issue"` en `serenity.conf`.
* **Razón**: Serenity BDD detecta la sintaxis `@tipo:valor` y agrupa automáticamente los escenarios bajo la sección "API" en el menú lateral y en el dashboard de requerimientos, calculando métricas de cobertura por endpoint.
* **Alternativas consideradas**:
  - Tags planos (`@api1`, `@api11`): No generan agrupación por tipo en el reporte de Serenity, apareciendo como tags genéricos mezclados con `@smoke` o `@regression`.

### Decisión 2: Integración de hipervínculos con `serenity.issue.tracker.url`
* **Elección**: Configurar `serenity.issue.tracker.url = "https://automationexercise.com/api_list#collapse{0}"` y etiquetar con `@issue:1` a `@issue:14`.
* **Razón**: La documentación de Automation Exercise utiliza identificadores de acordeón Bootstrap `#collapse1` hasta `#collapse14`. Serenity renderiza las etiquetas `@issue` como badges cliqueables en la interfaz web del reporte, abriendo el acordeón exacto de la API.
* **Alternativas consideradas**:
  - URLs manuales en el texto narrativo: No son interactivas en la lista de escenarios ni en las tablas de resultados individuales.

### Decisión 3: Nomenclatura uniforme de escenarios con prefijo
* **Elección**: Formatear los títulos de escenario como `[API-XX] [METHOD /path] <descripción>`.
* **Razón**: Proporciona visibilidad inmediata del endpoint y método en:
  1. Tabla de resultados de Serenity (`index.html` y `capabilities.html`).
  2. Reportes JUnit XML generados para CI/CD (GitHub Actions).
  3. Consola de ejecución de Gradle.
* **Alternativas consideradas**:
  - Palabras clave `Regla: API X`: Agrega un nivel de indentación en Gherkin sin aportar ventajas en los reportes de JUnit/CI.

### Matriz de Mapeo API 1 a 14

| API # | Método | Endpoint | Feature | Tag Serenity | Issue Tag |
|---|---|---|---|---|---|
| API 1 | GET | `/api/productsList` | `products_catalog.feature` | `@api:API-01` | `@issue:1` |
| API 2 | POST | `/api/productsList` | `products_catalog.feature` | `@api:API-02` | `@issue:2` |
| API 3 | GET | `/api/brandsList` | `brands_catalog.feature` | `@api:API-03` | `@issue:3` |
| API 4 | PUT | `/api/brandsList` | `brands_catalog.feature` | `@api:API-04` | `@issue:4` |
| API 5 | POST | `/api/searchProduct` | `search_product.feature` | `@api:API-05` | `@issue:5` |
| API 6 | POST | `/api/searchProduct` | `search_product.feature` | `@api:API-06` | `@issue:6` |
| API 7 | POST | `/api/verifyLogin` | `login_verification.feature` | `@api:API-07` | `@issue:7` |
| API 8 | POST | `/api/verifyLogin` | `login_verification.feature` | `@api:API-08` | `@issue:8` |
| API 9 | DELETE | `/api/verifyLogin` | `login_verification.feature` | `@api:API-09` | `@issue:9` |
| API 10 | POST | `/api/verifyLogin` | `login_verification.feature` | `@api:API-10` | `@issue:10` |
| API 11 | POST | `/api/createAccount` | `account_management.feature` | `@api:API-11` | `@issue:11` |
| API 12 | DELETE | `/api/deleteAccount` | `account_management.feature` | `@api:API-12` | `@issue:12` |
| API 13 | PUT | `/api/updateAccount` | `account_management.feature` | `@api:API-13` | `@issue:13` |
| API 14 | GET | `/api/getUserDetailByEmail` | `account_management.feature` | `@api:API-14` | `@issue:14` |

## Risks / Trade-offs

- **[Riesgo]** Filtros de ejecución previos en pipelines o runners que busquen `@api7` o `@api11`.
  - *Mitigación*: El runner principal `ApiTestSuiteRunner.java` utiliza tags de dominio (`@catalog or @auth or @account or @smoke or @regression`), por lo que no se ve afectado. Además, los tags tipados permiten filtros tanto por `@api:API-11` como por expresiones comodín.
- **[Riesgo]** Espacios o caracteres no válidos en nombres de tags de Serenity.
  - *Mitigación*: Se utiliza el formato estandarizado sin espacios `@api:API-XX` y `@issue:X`.

## Migration Plan

1. Actualizar `serenity.conf` con `tag.types` e `issue.tracker.url`.
2. Actualizar las características del catálogo de productos y marcas (API 1 a 6).
3. Actualizar las características de autenticación (API 7 a 10).
4. Actualizar las características de cuentas de usuario (API 11 a 14).
5. Ejecutar la suite completa y validar la generación del reporte Serenity (`.\gradlew clean test aggregate`).

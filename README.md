# 🚀 TransUnion API Automation Framework

[![CI/CD Pipeline](https://github.com/narvaezdario91/api-automation-transunion/actions/workflows/api-automation-ci.yml/badge.svg)](https://github.com/narvaezdario91/api-automation-transunion/actions/workflows/api-automation-ci.yml)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=narvaezdario91_api-automation-transunion&metric=alert_status)](https://sonarcloud.io/project/overview?id=narvaezdario91_api-automation-transunion)
[![Living Documentation](https://img.shields.io/badge/Living%20Docs-GitHub%20Pages-00a3e0.svg)](https://narvaezdario91.github.io/api-automation-transunion/)
[![Java 21](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/downloads/#java21)
[![Gradle](https://img.shields.io/badge/Gradle-8.8-blue.svg)](https://gradle.org/)
[![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-4.2.16-brightgreen.svg)](https://serenity-bdd.info/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.18.0-darkgreen.svg)](https://cucumber.io/)
[![REST-Assured](https://img.shields.io/badge/REST--Assured-5.4.0-red.svg)](https://rest-assured.io/)
[![Checkstyle](https://img.shields.io/badge/Checkstyle-10.17.0-brightgreen.svg)](https://checkstyle.org/)
[![Pattern](https://img.shields.io/badge/Architecture-Screenplay%20%2B%20SOLID-blueviolet.svg)]()

Framework empresarial de automatización de pruebas para servicios y APIs REST, diseñado bajo los más altos estándares de calidad, **principios SOLID**, **Screenplay Pattern**, análisis estático de código, Quality Gates y especificaciones ejecutables con **BDD (Cucumber)** sobre la suite de [Automation Exercise API List](https://automationexercise.com/api_list).

> ### 🌐 Living Documentation & Quality Portal
> - 🚀 **Portal Principal de Reportes:** [https://narvaezdario91.github.io/api-automation-transunion/](https://narvaezdario91.github.io/api-automation-transunion/)
> - 📄 **Reporte Rama `main` (Producción):** [Serenity Report `main`](https://narvaezdario91.github.io/api-automation-transunion/serenity-report/main/index.html)
> - ⚡ **Reporte Rama `develop` (Staging):** [Serenity Report `develop`](https://narvaezdario91.github.io/api-automation-transunion/serenity-report/develop/index.html)
> - 🛡️ **SonarCloud Dashboard:** [SonarCloud Project Overview](https://sonarcloud.io/project/overview?id=narvaezdario91_api-automation-transunion)

---

## 📋 Tabla de Contenidos
1. [Propósito del Proyecto](#-propósito-del-proyecto)
2. [Arquitectura y Patrones de Diseño](#-arquitectura-y-patrones-de-diseño)
3. [Matriz de Cobertura y Trazabilidad de APIs (Automation Exercise)](#-matriz-de-cobertura-y-trazabilidad-de-apis-automation-exercise)
4. [Estructura del Proyecto](#-estructura-del-proyecto)
5. [Requisitos Previos](#-requisitos-previos)
6. [Instalación y Configuración](#-instalación-y-configuración)
7. [Análisis Estático y Quality Gates](#-análisis-estático-y-quality-gates)
8. [Ejecución de Pruebas](#-ejecución-de-pruebas)
9. [Pipeline de CI/CD (GitHub Actions)](#-pipeline-de-cicd-github-actions)
10. [Reportes y Trazabilidad en Serenity BDD](#-reportes-y-trazabilidad-en-serenity-bdd)
11. [Buenas Prácticas](#-buenas-prácticas)
12. [Autores](#-autores)
13. [Licencia y Derechos](#-licencia-y-derechos)

---

## 🎯 Propósito del Proyecto

El objetivo principal de este framework es proporcionar una plataforma de pruebas API robusta, escalable, mantenible y legible tanto para perfiles técnicos como para stakeholders de negocio. 

Permite:
- Validar contratos de API en formato **JSON Schema**.
- Generar datos de prueba dinámicos y realistas con **Datafaker** evitando colisiones en ejecuciones concurrentes.
- Implementar desacoplamiento modular entre componentes universales (`core`) y lógica de negocio (`domain`).
- Proporcionar **trazabilidad completa** hacia la documentación oficial de [Automation Exercise API List](https://automationexercise.com/api_list) mediante tags tipados de Serenity (`@api:API-XX`), enlaces dinámicos (`@issue:X`) y nomenclatura de escenarios estandarizada (`[API-XX] [METHOD /endpoint]`).
- Ejecutar pruebas en diversos entornos de despliegue (`default`, `dev`, `qa`, `staging`).
- Generar documentación viva (*Living Documentation*) con detalle HTTP completo de cada Request y Response.

---

## 🏛️ Arquitectura y Patrones de Diseño

El framework implementa una arquitectura desacoplada basada en el **Screenplay Pattern** complementado con patrones de diseño clásicos y principios **SOLID**:

```
+-------------------------------------------------------------------------------+
|                                  TEST LAYER                                   |
|   - Feature Files (.feature) categorizados por dominio (catalog, auth, account)|
|   - Step Definitions desacoplados (Domain Steps + Universal Common Steps)     |
+-------------------------------------------------------------------------------+
                                        |
                                        v
+-------------------------------------------------------------------------------+
|                             BUSINESS / DOMAIN LAYER                           |
|   - Domains: Catalog, Authentication, Account Management                      |
|   - Domain Tasks & Facades (RegisterUser, QueryProducts, SearchProducts)      |
|   - Domain Questions & DTOs (UserDetailResponse, BrandResponse)               |
+-------------------------------------------------------------------------------+
                                        |
                                        v
+-------------------------------------------------------------------------------+
|                               CORE / ENGINE LAYER                             |
|   - Reusable Interactions (GenericRestInteractions, SendFormUrlencodedPost)   |
|   - Universal Questions (LastResponseStatusCode, ResponseBodyQuestion)        |
|   - Authentication Strategies (BearerToken, ApiKey, NoAuth - Strategy Pattern)|
|   - Data Factories (UserDataFactory - Factory Pattern con Datafaker)          |
+-------------------------------------------------------------------------------+
                                        |
                                        v
+-------------------------------------------------------------------------------+
|                             INFRASTRUCTURE & CONFIG                           |
|   - Multi-environment & Tag Taxonomy Configuration (serenity.conf)            |
|   - JSON Schema Validators (Draft-07 / Draft-04)                              |
|   - Serenity Living Documentation Engine & Issue Tracker Integration          |
+-------------------------------------------------------------------------------+
```

### Patrones de Diseño Integrados:

| Patrón | Dónde se aplica | Beneficio |
| :--- | :--- | :--- |
| **Screenplay** | `tasks/`, `interactions/`, `questions/` | Separa al **Actor** de sus **Habilidades**, **Tareas** y **Preguntas** asegurando alta reusabilidad y legibilidad de negocio. |
| **Builder** | `domain/*/models/` | Construcción inmutable y fluida de DTOs y payloads mediante Lombok (`@Builder`, `@Jacksonized`). |
| **Factory** | `core/data/` | Generación dinámica y aleatoria de datos de prueba válidos/inválidos con **Datafaker**. |
| **Strategy** | `core/auth/` | Intercambio polimórfico de mecanismos de autenticación sin modificar el cliente HTTP ni usar condicionales `if-else`. |
| **Facade** | `domain/*/tasks/` | Orquestación simplificada de múltiples tareas o precondiciones complejas (ej. asegurar usuario existente previo a consulta/actualización). |

---

## 📑 Matriz de Cobertura y Trazabilidad de APIs (Automation Exercise)

El framework cubre al 100% los 14 endpoints descritos en [Automation Exercise API List](https://automationexercise.com/api_list). Cada escenario cuenta con tags tipados (`@api:API-XX`), hipervínculo directo al acordeón oficial (`@issue:X` $\rightarrow$ `#collapseX`) y título descriptivo:

| API # | Método | Endpoint Oficial | Feature BDD | Tag Serenity | Issue Link | Tipos de Prueba |
| :---: | :---: | :--- | :--- | :---: | :---: | :--- |
| **API 1** | `GET` | `/api/productsList` | `catalog/products_catalog.feature` | `@api:API-01` | `@issue:1` | `@smoke`, `@regression`, `@contract` |
| **API 2** | `POST` | `/api/productsList` | `catalog/products_catalog.feature` | `@api:API-02` | `@issue:2` | `@regression`, `@negative` |
| **API 3** | `GET` | `/api/brandsList` | `catalog/brands_catalog.feature` | `@api:API-03` | `@issue:3` | `@smoke`, `@regression`, `@contract` |
| **API 4** | `PUT` | `/api/brandsList` | `catalog/brands_catalog.feature` | `@api:API-04` | `@issue:4` | `@regression`, `@negative` |
| **API 5** | `POST` | `/api/searchProduct` | `catalog/search_product.feature` | `@api:API-05` | `@issue:5` | `@smoke`, `@regression` (Scenario Outline) |
| **API 6** | `POST` | `/api/searchProduct` (sin param) | `catalog/search_product.feature` | `@api:API-06` | `@issue:6` | `@regression`, `@negative` |
| **API 7** | `POST` | `/api/verifyLogin` (válido) | `auth/login_verification.feature` | `@api:API-07` | `@issue:7` | `@smoke`, `@regression`, `@contract` |
| **API 8** | `POST` | `/api/verifyLogin` (sin email) | `auth/login_verification.feature` | `@api:API-08` | `@issue:8` | `@regression`, `@negative` |
| **API 9** | `DELETE`| `/api/verifyLogin` (no soportado)| `auth/login_verification.feature` | `@api:API-09` | `@issue:9` | `@regression`, `@negative` |
| **API 10**| `POST` | `/api/verifyLogin` (inválido) | `auth/login_verification.feature` | `@api:API-10` | `@issue:10`| `@regression`, `@negative` |
| **API 11**| `POST` | `/api/createAccount` | `account/account_management.feature` | `@api:API-11` | `@issue:11`| `@smoke`, `@regression`, `@negative` |
| **API 12**| `DELETE`| `/api/deleteAccount` | `account/account_management.feature` | `@api:API-12` | `@issue:12`| `@regression`, `@negative` |
| **API 13**| `PUT` | `/api/updateAccount` | `account/account_management.feature` | `@api:API-13` | `@issue:13`| `@regression`, `@requires_user` |
| **API 14**| `GET` | `/api/getUserDetailByEmail` | `account/account_management.feature` | `@api:API-14` | `@issue:14`| `@smoke`, `@regression`, `@contract`, `@negative` |

---

## 📁 Estructura del Proyecto

```
api-automation-transunion/
├── src/
│   ├── main/java/com/transunion/automation/
│   │   ├── core/                               # Módulos transversales reutilizables
│   │   │   ├── auth/                           # Estrategias de autenticación (Strategy Pattern)
│   │   │   │   ├── AuthenticationStrategy.java
│   │   │   │   ├── BearerTokenAuthStrategy.java
│   │   │   │   ├── ApiKeyAuthStrategy.java
│   │   │   │   └── NoAuthStrategy.java
│   │   │   ├── constants/                      # Constantes y Endpoints globales
│   │   │   ├── data/                           # Fábrica dinámica de datos (UserDataFactory)
│   │   │   ├── exceptions/                     # Excepciones personalizadas
│   │   │   ├── interactions/                   # Interacciones Screenplay (FormUrlencoded, Generic REST)
│   │   │   ├── questions/                      # Preguntas universales (StatusCode, ResponseBodyQuestion)
│   │   │   └── tasks/                          # Tareas transversales del core
│   │   │
│   │   └── domain/                             # Lógica de negocio segmentada por dominio
│   │       ├── account/                        # Dominio: Gestión de Cuentas (API 11, 12, 13, 14)
│   │       │   ├── models/                     # DTOs de cuenta
│   │       │   ├── questions/                  # Validaciones de perfil
│   │       │   └── tasks/                      # Tareas (CreateAccount, DeleteAccount, etc.)
│   │       ├── auth/                           # Dominio: Autenticación y Login (API 7, 8, 9, 10)
│   │       │   └── tasks/                      # Tareas de verificación de login
│   │       └── catalog/                        # Dominio: Catálogo de Productos y Marcas (API 1 a 6)
│   │           ├── models/                     # DTOs de productos y marcas
│   │           ├── questions/                  # Validaciones de catálogo
│   │           └── tasks/                      # Tareas de búsqueda y consulta
│   │
│   └── test/
│       ├── java/com/transunion/automation/
│       │   ├── runners/                        # Test Runner con Cucumber y Serenity
│       │   │   └── ApiTestSuiteRunner.java
│       │   └── stepdefinitions/                # Glue code desacoplado (Gherkin -> Screenplay)
│       │       ├── account/AccountStepDefinitions.java
│       │       ├── auth/LoginStepDefinitions.java
│       │       ├── catalog/CatalogStepDefinitions.java
│       │       └── common/
│       │           ├── CommonApiStepDefinitions.java # Aserciones de status, schemas, mensajes compartidos
│       │           └── CommonHooks.java
│       └── resources/
│           ├── features/                       # Especificaciones BDD ejecutables (.feature)
│           │   ├── account/account_management.feature
│           │   ├── auth/login_verification.feature
│           │   └── catalog/
│           │       ├── brands_catalog.feature
│           │       ├── products_catalog.feature
│           │       └── search_product.feature
│           ├── schemas/                        # Contratos de validación JSON Schema
│           │   ├── account/user_detail_schema.json
│           │   ├── auth/login_response_schema.json
│           │   └── catalog/
│           │       ├── brands_list_schema.json
│           │       └── products_list_schema.json
│           ├── serenity.conf                   # Configuración multi-entorno, taxonomía de tags e issue links
│           └── logback-test.xml                # Configuración de logs
├── config/checkstyle/                          # Reglas de estilo Checkstyle
├── openspec/                                   # Especificaciones OpenSpec de arquitectura y requerimientos
├── build.gradle                                # Dependencias y configuración de plugins Gradle
├── gradle.properties                           # Versiones y parámetros JVM
├── settings.gradle                             # Configuración del proyecto
└── README.md
```

---

## 🛠️ Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de contar con:
- **Java JDK 21** instalado (ejemplo: [Eclipse Adoptium Temurin 21](https://adoptium.net/temurin/releases/?version=21) u Oracle OpenJDK 21).
- Variable de entorno `JAVA_HOME` apuntando al JDK 21.
- Conexión a Internet para la descarga inicial de dependencias vía Gradle Wrapper.

---

## ⚙️ Instalación y Configuración

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/narvaezdario91/api-automation-transunion.git
   cd api-automation-transunion
   ```

2. **Verificar el compilador y dependencias:**
   ```powershell
   # En Windows
   .\gradlew.bat compileJava compileTestJava

   # En Linux / macOS
   ./gradlew compileJava compileTestJava
   ```

---

## 🔍 Análisis Estático y Quality Gates

El proyecto integra validación de calidad de código y detección temprana de malas prácticas:

### 1. Checkstyle (Reglas de Estilo y Clean Code)
Verifica formato, convenciones de nomenclatura Java, imports no utilizados y estructura de bloques de código configurados en `config/checkstyle/checkstyle.xml`:
```powershell
# Ejecutar verificación de estilo en código fuente y pruebas
.\gradlew.bat checkstyleMain checkstyleTest
```

### 2. SonarQube / SonarCloud (Quality Gate)
Analiza vulnerabilidades, bugs potenciales, code smells y deuda técnica:
```powershell
# Ejecutar análisis de Sonar localmente
.\gradlew.bat sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.token=TU_TOKEN
```

---

## 🏃 Ejecución de Pruebas

El framework incluye el wrapper de Gradle (`gradlew`), por lo que no necesitas instalar Gradle de forma global.

### 1. Ejecución completa con reporte agregado
Ejecuta la suite completa de 17 casos de prueba y genera el reporte consolidado de Serenity:
```powershell
.\gradlew.bat clean test aggregate
```

### 2. Filtrar ejecución por Tags de Cucumber

Puedes filtrar escenarios con máxima granularidad según el endpoint, dominio o tipo de prueba:

* **Por Endpoint específico (API 1 a 14):**
  ```powershell
  # Ejecutar exclusivamente la API 1 (Get All Products List)
  .\gradlew.bat test -Dcucumber.filter.tags="@api:API-01" aggregate

  # Ejecutar la API 11 (Create Account)
  .\gradlew.bat test -Dcucumber.filter.tags="@api:API-11" aggregate
  ```

* **Por Dominio de Negocio:**
  ```powershell
  # Catálogo de productos y marcas (API 1 a 6)
  .\gradlew.bat test -Dcucumber.filter.tags="@catalog" aggregate

  # Verificación de Login y Autenticación (API 7 a 10)
  .\gradlew.bat test -Dcucumber.filter.tags="@auth" aggregate

  # Gestión de Cuentas de Usuario (API 11 a 14)
  .\gradlew.bat test -Dcucumber.filter.tags="@account" aggregate
  ```

* **Por Tipo de Prueba:**
  ```powershell
  # Pruebas de Humo (Smoke)
  .\gradlew.bat test -Dcucumber.filter.tags="@smoke" aggregate

  # Validación de Contratos y Esquemas JSON (Contract)
  .\gradlew.bat test -Dcucumber.filter.tags="@contract" aggregate

  # Pruebas de Flujos Negativos y Códigos de Error (Negative)
  .\gradlew.bat test -Dcucumber.filter.tags="@negative" aggregate

  # Suite de Regresión Completa
  .\gradlew.bat test -Dcucumber.filter.tags="@regression" aggregate
  ```

* **Combinación lógica de Tags:**
  ```powershell
  .\gradlew.bat test -Dcucumber.filter.tags="@smoke and @catalog" aggregate
  .\gradlew.bat test -Dcucumber.filter.tags="@regression and not @negative" aggregate
  ```

### 3. Ejecución por Ambientes de Despliegue
Selecciona el entorno configurado en `serenity.conf` (`default`, `dev`, `qa`, `staging`):
```powershell
.\gradlew.bat test -Denvironment=qa aggregate
```

### 4. Ejecución desde IDEs
* **IntelliJ IDEA / Eclipse / VS Code:**
  * Abre [`ApiTestSuiteRunner.java`](file:///src/test/java/com/transunion/automation/runners/ApiTestSuiteRunner.java), clic derecho $\rightarrow$ **Run 'ApiTestSuiteRunner'**.
  * O abre directamente cualquier archivo `.feature` y ejecuta el escenario deseado individualmente.

---

## 🚀 Pipeline de CI/CD (GitHub Actions)

El archivo `.github/workflows/api-automation-ci.yml` orquesta la integración y despliegue continuo con las siguientes capacidades:

```
+-----------------------------------------------------------------------------------------+
|                                GitHub Actions Workflow                                  |
+-----------------------------------------------------------------------------------------+
|  [ Triggers ]                                                                           |
|   • Push & Pull Requests a 'main' (Ejecución completa automática).                      |
|   • workflow_dispatch (Ejecución manual parametrizada por 'environment' y 'tags').      |
|                                                                                         |
|  [ Job 1: Static Analysis & Quality Gate ]                                              |
|   • Setup JDK 21 (Temurin) + Gradle Cache.                                              |
|   • Checkstyle validation (`checkstyleMain`, `checkstyleTest`).                         |
|   • SonarQube / SonarCloud Scanner con evaluación de Quality Gate.                      |
|                                                                                         |
|  [ Job 2: API Test Execution & Reporting ]                                              |
|   • Ejecución de pruebas Serenity BDD con filtrado dinámico.                            |
|   • Carga de artefactos `target/site/serenity` y `build/test-results/test`.             |
|   • Publicación automática de reportes HTML en GitHub Pages (`gh-pages`).               |
|   • Resumen visual en GITHUB_STEP_SUMMARY.                                              |
+-----------------------------------------------------------------------------------------+
```

### Ejecución Manual desde GitHub (`workflow_dispatch`):
1. Navega a la pestaña **Actions** en el repositorio de GitHub.
2. Selecciona **API Automation CI/CD Pipeline**.
3. Haz clic en **Run workflow**.
4. Configura los parámetros:
   - **Target Execution Environment**: `default`, `dev`, `qa` o `staging`.
   - **Cucumber Tag Expression**: Expresión de tags (ej. `@api:API-01`, `@catalog`, `@smoke` o en blanco para la suite completa).
5. Haz clic en **Run workflow** para iniciar la ejecución.

---

## 📊 Reportes y Trazabilidad en Serenity BDD

Serenity BDD genera un reporte interactivo de calidad industrial con:
1. **Taxonomía de Tags "API"**: Sección dedicada en el menú de navegación que agrupa los escenarios por cada endpoint (`API-01` a `API-14`) mostrando métricas de estabilidad, porcentaje de éxito y tiempos de respuesta.
2. **Hipervínculos a la Documentación Oficial**: Cada escenario presenta un badge interactivo (`@issue:X`) que al hacer clic abre directamente el acordeón de la documentación en [Automation Exercise API List](https://automationexercise.com/api_list#collapse1).
3. **Nomenclatura Estandarizada**: Todos los escenarios se muestran como `[API-XX] [METHOD /endpoint] <descripción>` para rápida identificación en dashboards y consolas de CI/CD.
4. **Detalle de Tráfico REST**: Inspección exhaustiva de cada interacción HTTP (URL, Método, Headers enviados/recibidos, Payload y Response Body con resaltado sintáctico).

Para abrir el reporte generado localmente tras la ejecución:

```powershell
# En Windows PowerShell
Start-Process target/site/serenity/index.html

# En Linux
xdg-open target/site/serenity/index.html

# En macOS
open target/site/serenity/index.html
```

---

## 💡 Buenas Prácticas

1. **Aislamiento e Independencia:** Cada escenario prepara sus propios datos con `UserDataFactory` para garantizar que pueda ejecutarse de forma independiente y paralela sin colisiones de identificadores.
2. **Uso de Preguntas (Questions):** No utilices aserciones directas de JUnit dentro de los Step Definitions. Formula preguntas del Screenplay (`actor.should(seeThat(...))`) para mantener la trazabilidad y la semántica en los reportes.
3. **Reutilización de Step Definitions:** Los pasos universales de verificación de códigos de estado HTTP, validación de esquemas JSON y mensajes se resuelven en `CommonApiStepDefinitions`, evitando duplicación de código entre dominios.
4. **Validación de Esquemas:** Incorpora siempre la validación contra esquemas JSON (`schemas/*/*.json`) para detectar cambios que rompan compatibilidad en los contratos API.
5. **Manejo de Secretos:** Nunca expongas credenciales en código fuente; pásalas como variables de entorno o mediante parámetros en tiempo de ejecución.

---

## 👥 Autores

* **Dario Narvaez** - *Lead SDET / Software Development Engineer in Test* - [GitHub Profile](https://github.com/narvaezdario91)

---

## 📄 Licencia y Derechos

Copyright © 2026 **Dario Narvaez**. Todos los derechos reservados.

El código, arquitectura y documentación contenidos en este repositorio son propiedad y autoría de **Dario Narvaez** y están destinados para fines de automatización y aseguramiento de calidad de software (QA Automation).

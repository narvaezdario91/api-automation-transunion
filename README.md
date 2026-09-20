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

Framework empresarial de automatización de pruebas para servicios y APIs REST, diseñado bajo los más altos estándares de calidad, **principios SOLID**, **Screenplay Pattern**, análisis estático de código, Quality Gates y especificaciones ejecutables con **BDD (Cucumber)**.

> ### 🌐 Living Documentation & Quality Portal
> - 🚀 **Portal Principal de Reportes:** [https://narvaezdario91.github.io/api-automation-transunion/](https://narvaezdario91.github.io/api-automation-transunion/)
> - 📄 **Reporte Rama `main` (Producción):** [Serenity Report `main`](https://narvaezdario91.github.io/api-automation-transunion/serenity-report/main/index.html)
> - ⚡ **Reporte Rama `develop` (Staging):** [Serenity Report `develop`](https://narvaezdario91.github.io/api-automation-transunion/serenity-report/develop/index.html)
> - 🛡️ **SonarCloud Dashboard:** [SonarCloud Project Overview](https://sonarcloud.io/project/overview?id=narvaezdario91_api-automation-transunion)

---

## 📋 Tabla de Contenidos
1. [Propósito del Proyecto](#-propósito-del-proyecto)
2. [Arquitectura y Patrones de Diseño](#-arquitectura-y-patrones-de-diseño)
3. [Estructura del Proyecto](#-estructura-del-proyecto)
4. [Requisitos Previos](#-requisitos-previos)
5. [Instalación y Configuración](#-instalación-y-configuración)
6. [Análisis Estático y Quality Gates](#-análisis-estático-y-quality-gates)
7. [Ejecución de Pruebas](#-ejecución-de-pruebas)
8. [Pipeline de CI/CD (GitHub Actions)](#-pipeline-de-cicd-github-actions)
9. [Reportes y Evidencias](#-reportes-y-evidencias)
10. [Buenas Prácticas](#-buenas-prácticas)
11. [Autores](#-autores)
12. [Licencia y Derechos](#-licencia-y-derechos)

---

## 🎯 Propósito del Proyecto

El objetivo principal de este framework es proporcionar una plataforma de pruebas API robusta, escalable, mantenible y legible tanto para perfiles técnicos como para stakeholders de negocio. 

Permite:
- Validar contratos de API en formato **JSON Schema**.
- Generar datos de prueba dinámicos y realistas evitando colisiones en ejecuciones paralelas.
- Manejar múltiples estrategias de autenticación (Bearer Token, API Key, Basic Auth).
- Ejecutar pruebas en diversos entornos de despliegue (`dev`, `qa`, `staging`, `prod`).
- Generar documentación viva (*Living Documentation*) con trazabilidad completa de cada Request y Response HTTP.

---

## 🏛️ Arquitectura y Patrones de Diseño

El framework implementa una arquitectura desacoplada basada en el **Screenplay Pattern** complementado con patrones de diseño clásicos y principios **SOLID**:

```
+-------------------------------------------------------------+
|                        TEST LAYER                           |
|   - Feature Files (.feature) & Step Definitions             |
+-------------------------------------------------------------+
                              |
                              v
+-------------------------------------------------------------+
|                     BUSINESS / ACTOR LAYER                  |
|   - Actors & Abilities (CallAnApi)                          |
|   - Tasks & Facades (CreateUser, UserOnboardingFacade)      |
|   - Questions (LastResponseStatusCode, ResponseSchema)      |
+-------------------------------------------------------------+
                              |
                              v
+-------------------------------------------------------------+
|                     CORE / CLIENT LAYER                     |
|   - Interactions (Post, Get, Put, Delete)                   |
|   - Authentication Strategies (Bearer, ApiKey, NoAuth)      |
|   - DTOs / Payloads (Builder Pattern con Lombok)            |
+-------------------------------------------------------------+
                              |
                              v
+-------------------------------------------------------------+
|                    INFRASTRUCTURE & CONFIG                  |
|   - Multi-environment Configuration (serenity.conf)         |
|   - Dynamic Data Factories (Datafaker)                      |
|   - JSON Schema Validators                                  |
|   - Serenity Living Documentation Reports                   |
+-------------------------------------------------------------+
```

### Patrones de Diseño Integrados:

| Patrón | Dónde se aplica | Beneficio |
| :--- | :--- | :--- |
| **Screenplay** | `tasks/`, `interactions/`, `questions/` | Separa al **Actor** de sus **Habilidades**, **Tareas** y **Preguntas** asegurando alta reusabilidad y legibilidad de negocio. |
| **Builder** | `models/request/`, `models/response/` | Construcción inmutable y fluida de payloads mediante Lombok (`@Builder`, `@Jacksonized`). |
| **Factory** | `utils/data/` | Generación dinámica y aleatoria de datos de prueba válidos/inválidos con **Datafaker**. |
| **Strategy** | `interactions/auth/` | Intercambio polimórfico de mecanismos de autenticación sin modificar el cliente HTTP ni usar `if-else`. |
| **Facade** | `tasks/facades/` | Orquestación simplificada de múltiples tareas o precondiciones complejas previas a las pruebas. |

---

## 📁 Estructura del Proyecto

```
api-automation-transunion/
├── src/
│   ├── main/java/com/transunion/automation/
│   │   ├── exceptions/                 # Excepciones de negocio y aserciones
│   │   ├── interactions/auth/          # Estrategias de autenticación (Strategy Pattern)
│   │   │   ├── AuthenticationStrategy.java
│   │   │   ├── BearerTokenAuthStrategy.java
│   │   │   ├── ApiKeyAuthStrategy.java
│   │   │   └── NoAuthStrategy.java
│   │   ├── models/                     # DTOs y modelos de datos (Builder Pattern)
│   │   │   ├── request/UserRequestDto.java
│   │   │   └── response/UserResponseDto.java
│   │   ├── questions/                  # Validaciones y aserciones (Screenplay Questions)
│   │   │   ├── LastResponseStatusCode.java
│   │   │   ├── ResponseSchemaMatches.java
│   │   │   └── UserResponseBody.java
│   │   ├── tasks/                      # Tareas de negocio y orquestadores (Tasks & Facades)
│   │   │   ├── facades/UserOnboardingFacade.java
│   │   │   └── user/
│   │   │       ├── CreateUser.java
│   │   │       └── QueryUserById.java
│   │   └── utils/
│   │       ├── constants/Endpoints.java
│   │       └── data/UserDataFactory.java (Factory Pattern)
│   │
│   └── test/
│       ├── java/com/transunion/automation/
│       │   ├── runners/                # Test Runners de Cucumber con Serenity
│       │   │   └── ApiTestSuiteRunner.java
│       │   └── stepdefinitions/        # Glue code (Gherkin -> Actor)
│       │       ├── CommonHooks.java
│       │       └── UserStepDefinitions.java
│       └── resources/
│           ├── features/               # Especificaciones BDD (.feature)
│           │   └── user/create_user.feature
│           ├── schemas/                # Contratos JSON Schema
│           │   └── user_schema.json
│           ├── serenity.conf           # Configuración multi-ambiente
│           └── logback-test.xml        # Configuración de logs
├── build.gradle                        # Dependencias y tareas Gradle
├── gradle.properties                   # Versiones y configuración de JVM
├── settings.gradle                     # Configuración de plugins y proyecto
└── README.md
```

---

## 🛠️ Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de contar con:
- **Java JDK 21** instalado (ejemplo: [Eclipse Temurin 21](https://adoptium.net/temurin/releases/?version=21) u Oracle OpenJDK 21).
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

### 1. Ejecución completa con reporte
Ejecuta todas las pruebas y genera el reporte agregado de Serenity:
```powershell
.\gradlew.bat clean test aggregate
```

### 2. Filtrar ejecución por Tags de Cucumber
Ejecuta únicamente suites etiquetadas en los archivos `.feature`:

* **Pruebas de Humo (Smoke Tests):**
  ```powershell
  .\gradlew.bat test -Dcucumber.filter.tags="@smoke" aggregate
  ```
* **Validación de Esquemas y Contratos (Contract Tests):**
  ```powershell
  .\gradlew.bat test -Dcucumber.filter.tags="@contract" aggregate
  ```
* **Pruebas de Regresión:**
  ```powershell
  .\gradlew.bat test -Dcucumber.filter.tags="@regression" aggregate
  ```
* **Combinación lógica de Tags:**
  ```powershell
  .\gradlew.bat test -Dcucumber.filter.tags="@user-management and not @ignore" aggregate
  ```

### 3. Ejecución por Ambientes de Despliegue
Selecciona el entorno configurado en `serenity.conf` (`default`, `dev`, `qa`, `staging`):
```powershell
.\gradlew.bat test -Denvironment=qa aggregate
```

### 4. Ejecución desde IDEs
* **IntelliJ IDEA / Eclipse / VS Code:**
  * Abre [`ApiTestSuiteRunner.java`](file:///src/test/java/com/transunion/automation/runners/ApiTestSuiteRunner.java), clic derecho $\rightarrow$ **Run 'ApiTestSuiteRunner'**.
  * O abre directamente cualquier archivo `.feature` y ejecuta el escenario deseado.

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
1. Navega a la pestaña **Actions** en tu repositorio de GitHub.
2. Selecciona **API Automation CI/CD Pipeline**.
3. Haz clic en **Run workflow**.
4. Configura los parámetros:
   - **Target Execution Environment**: `default`, `dev`, `qa` o `staging`.
   - **Cucumber Tag Expression**: Expresión de tags (ej. `@user`, `@smoke and not @manual` o en blanco para la suite completa).
5. Haz clic en **Run workflow** para iniciar la ejecución.

---

## 📊 Reportes y Evidencias

Serenity BDD genera un reporte interactivo con métricas, gráficos de dona, tiempos de respuesta y el detalle HTTP de cada interacción (Headers, Payload, Status Code, Body).

Para abrir el reporte generado tras la ejecución:

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

1. **Aislamiento e Independencia:** Cada escenario prepara sus propios datos con `UserDataFactory` para garantizar que pueda ejecutarse de forma independiente y paralela.
2. **Uso de Preguntas (Questions):** No utilices aserciones directas de JUnit dentro de los Step Definitions. Formula preguntas del Screenplay (`actor.should(seeThat(...))`) para mantener la trazabilidad en los reportes.
3. **Validación de Esquemas:** Incorpora siempre la validación contra esquemas JSON (`schemas/*.json`) para detectar cambios que rompan compatibilidad en los contratos API.
4. **Manejo de Secretos:** Nunca expongas credenciales en código fuente; pásalas como variables de entorno o mediante parámetros en tiempo de ejecución.

---

## 👥 Autores

* **Dario Narvaez** - *Lead SDET / Software Development Engineer in Test* - [GitHub Profile](https://github.com/narvaezdario91)

---

## 📄 Licencia y Derechos

Copyright © 2026 **Dario Narvaez**. Todos los derechos reservados.

El código, arquitectura y documentación contenidos en este repositorio son propiedad y autoría de **Dario Narvaez** y están destinados para fines de automatización y aseguramiento de calidad de software (QA Automation).

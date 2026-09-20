# Proposal: Fase 2 - Automatización de API para Autenticación y Verificación de Login

## Why
Implementar la automatización de pruebas BDD para las APIs de autenticación y verificación de login (APIs 7 a 10) de Automation Exercise (`https://automationexercise.com/api_list`), validando credenciales válidas, credenciales inválidas, omisión de parámetros obligatorios y control de métodos HTTP no soportados con Serenity BDD y Screenplay.

## What Changes
- Implementar pruebas automatizadas BDD en español para los endpoints de verificación de login:
  - `POST /api/verifyLogin` con credenciales válidas (API 7: `200 OK`, "User exists!").
  - `POST /api/verifyLogin` sin parámetro email (API 8: `400 Bad Request`, error de parámetro faltante).
  - `DELETE /api/verifyLogin` (API 9: `405 Method Not Allowed`, método no soportado).
  - `POST /api/verifyLogin` con credenciales inválidas (API 10: `404 Not Found`, "User not found!").
- Agregar el endpoint `VERIFY_LOGIN = "/api/verifyLogin"` en `Endpoints.java`.
- Implementar modelo DTO `LoginCredentialsDto` o parámetros fluidos en la Task `VerifyLogin`.
- Implementar Task Screenplay `VerifyLogin` (`withCredentials`, `withoutEmail`, `withInvalidCredentials`).
- Reutilizar `ExecuteUnsupportedMethod`, `ApiResponseCode` y `ApiResponseMessage`.
- Crear `login_verification.feature` en español con `# language: es` y vincular sus step definitions en `LoginStepDefinitions.java`.

## Capabilities

### New Capabilities
- `user-authentication`: Provee cobertura de pruebas de API para los endpoints de verificación de credenciales de usuario y autenticación de Automation Exercise.

### Modified Capabilities
<!-- No changes to existing capability specifications -->

## Impact
- **Endpoints**: Se añade `VERIFY_LOGIN` a `Endpoints.java`.
- **Tasks & Steps**: Nuevas Screenplay tasks bajo `tasks.auth` y Step Definitions bajo `stepdefinitions.auth`.
- **Features**: Nueva feature `login_verification.feature` en `src/test/resources/features/auth/`.

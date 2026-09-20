# Tasks

## 1. Factory y modelos de datos

- [x] 1.1 Añadir el método `withEmailPasswordAndName(String email, String password, String name)` en `AccountDataFactory.java`, que construya `AccountData` via builder con todos los campos requeridos y el `name` proporcionado. Verificar que el método compila y retorna un `AccountData` no nulo con el name esperado.

## 2. Step Definitions — Refactor y nuevos pasos

- [x] 2.1 Refactorizar el step `elActorActualizaLosDatosDelPerfilParaElUsuarioConNombre` en `AccountStepDefinitions.java` para usar `AccountDataFactory.withEmailPasswordAndName(email, DEFAULT_PASSWORD, name)` en lugar de llamar `setName()` sobre un objeto ya construido. Verificar que Checkstyle no reporta violaciones y que el paso compila.
- [x] 2.2 Añadir los siguientes steps en `AccountStepDefinitions.java` para cubrir escenarios negativos:
  - `elActorCreaUnaCuentaDeUsuarioDuplicada()` — crea un `dynamicUser`, lo almacena en memoria del actor, intenta crearlo con `CreateAccount`, y luego intenta crearlo por segunda vez con los mismos datos.
  - `elActorConsultaLosDetallesDeUnEmailInexistente(String email)` — ejecuta `GetUserDetail.forEmail(email)`.
  - `elActorEliminaUnaCuentaConCredencialesInvalidas()` — ejecuta `DeleteAccount.withCredentials("invalid@notexist.com", "WrongPass123!")`.
  - Verificar que todos los steps compilan sin errores.

## 3. JSON Schema — Login response

- [x] 3.1 Crear el archivo `src/test/resources/schemas/auth/login_response_schema.json` con el schema JSON que valida la estructura `{ "responseCode": integer, "message": string }` requerida por el endpoint `/api/verifyLogin`. Verificar que el archivo es JSON válido.

## 4. Feature files — Escenarios negativos de account

- [x] 4.1 Añadir en `account_management.feature` el escenario `@regression @negative @api11`: intento de crear una cuenta con email duplicado, con los pasos: `Dado que el actor está listo...`, `Y el actor crea una cuenta de usuario dinámica para eliminación`, `Cuando el actor intenta crear la misma cuenta de usuario nuevamente`, `Entonces el código de respuesta en el cuerpo debe ser 400`.
- [x] 4.2 Añadir en `account_management.feature` el escenario `@regression @negative @api14`: consulta de usuario con email no existente, verificando que el código de respuesta en el cuerpo es 404. Usar un email hardcoded como `"no_existe_jamas@testonly.invalid"`.
- [x] 4.3 Añadir en `account_management.feature` el escenario `@regression @negative @api12`: eliminación con credenciales inválidas, verificando que el código de respuesta en el cuerpo NO es 200. Verificar que todos los escenarios nuevos tienen su glue step implementado.

## 5. Feature files — Contratos JSON en search y login

- [x] 5.1 Añadir en el `Esquema del escenario` de búsqueda exitosa en `search_product.feature` el paso `Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/catalog/products_list_schema.json"`. El step ya existe en `CatalogStepDefinitions.java` — solo se añade el paso en el feature. Verificar que el escenario compila.
- [x] 5.2 Añadir en el escenario `@api7` de `login_verification.feature` el paso `Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/auth/login_response_schema.json"`. Verificar que el step existente en `CatalogStepDefinitions.java` resuelve el nuevo path correctamente.

## 6. Verificación integral

- [x] 6.1 Ejecutar `./gradlew test -Dcucumber.filter.tags="@account"` y confirmar que todos los escenarios de `account_management.feature` pasan (incluyendo los 3 nuevos negativos). Ajustar los códigos de respuesta esperados si la API externa retorna valores distintos a los estimados.
- [x] 6.2 Ejecutar `./gradlew test -Dcucumber.filter.tags="@smoke or @regression"` y confirmar que la suite completa pasa con 0 fallos. Verificar que Checkstyle reporta 0 warnings.

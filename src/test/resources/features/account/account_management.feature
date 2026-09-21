# language: es
@account @users
Característica: Gestión del Ciclo de Vida de Cuentas de Usuario
  Como consumidor de la API de Automation Exercise
  Quiero gestionar cuentas de usuario mediante los endpoints /api/createAccount, /api/deleteAccount, /api/updateAccount y /api/getUserDetailByEmail
  Para asegurar el registro, consulta de detalles, actualización y eliminación de cuentas

  @smoke @regression @api:API-11 @issue:11
  Escenario: [API-11] [POST /api/createAccount] Crear una cuenta de usuario exitosamente con datos válidos
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor envía una solicitud para crear una nueva cuenta con datos válidos
    Entonces el mensaje de respuesta debe ser "User created!"
    Y el código de respuesta en el cuerpo debe ser 201
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/common/api_response_schema.json"

  @smoke @regression @api:API-14 @issue:14 @requires_user
  Escenario: [API-14] [GET /api/getUserDetailByEmail] Consultar los detalles del perfil de un usuario existente por email
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor consulta los detalles del usuario con email "transunion.automation.test@gmail.com"
    Entonces el código de estado de la respuesta debe ser 200
    Y el código de respuesta en el cuerpo debe ser 200
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/account/user_detail_schema.json"
    Y los detalles del usuario consultado deben ser válidos para el email "transunion.automation.test@gmail.com"

  @regression @api:API-13 @issue:13 @requires_user
  Escenario: [API-13] [PUT /api/updateAccount] Actualizar los datos del perfil de una cuenta existente
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor actualiza los datos del perfil para el usuario "transunion.automation.test@gmail.com" con nombre "TransUnion Automation Updated"
    Entonces el mensaje de respuesta debe ser "User updated!"
    Y el código de respuesta en el cuerpo debe ser 200
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/common/api_response_schema.json"

  @regression @api:API-12 @issue:12
  Escenario: [API-12] [DELETE /api/deleteAccount] Eliminar una cuenta de usuario creada con credenciales válidas
    Dado que el actor está listo para consumir la API de Automation Exercise
    Y el actor crea una cuenta de usuario dinámica para eliminación
    Cuando el actor elimina la cuenta de usuario creada
    Entonces el mensaje de respuesta debe ser "Account deleted!"
    Y el código de respuesta en el cuerpo debe ser 200
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/common/api_response_schema.json"

  @regression @negative @api:API-11 @issue:11
  Escenario: [API-11] [POST /api/createAccount] Intentar crear una cuenta con un email duplicado
    Dado que el actor está listo para consumir la API de Automation Exercise
    Y el actor crea una cuenta de usuario dinámica para eliminación
    Cuando el actor intenta crear la misma cuenta de usuario nuevamente
    Entonces el código de respuesta en el cuerpo debe ser 400
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/common/api_response_schema.json"

  @regression @negative @api:API-11 @issue:11
  Escenario: [API-11] [POST /api/createAccount] Intentar crear una cuenta sin proporcionar los campos obligatorios
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor intenta crear una cuenta sin proporcionar los campos obligatorios
    Entonces el mensaje de respuesta debe ser "Bad request, email parameter is missing in POST request."
    Y el código de respuesta en el cuerpo debe ser 400
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/common/api_response_schema.json"

  @regression @negative @api:API-14 @issue:14
  Escenario: [API-14] [GET /api/getUserDetailByEmail] Consultar detalles de un usuario con email no existente
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor consulta los detalles de un usuario con email inexistente "no_existe_jamas@testonly.invalid"
    Entonces el código de respuesta en el cuerpo debe ser 404
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/common/api_response_schema.json"

  @regression @negative @api:API-13 @issue:13
  Escenario: [API-13] [PUT /api/updateAccount] Intentar actualizar una cuenta con un email inexistente
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor intenta actualizar los datos de la cuenta para un usuario inexistente "no_existe_jamas@testonly.invalid"
    Entonces el mensaje de respuesta debe ser "Account not found!"
    Y el código de respuesta en el cuerpo debe ser 404
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/common/api_response_schema.json"

  @regression @negative @api:API-12 @issue:12
  Escenario: [API-12] [DELETE /api/deleteAccount] Intentar eliminar una cuenta sin proporcionar el parámetro contraseña
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor intenta eliminar una cuenta sin proporcionar la contraseña para el email "transunion.automation.test@gmail.com"
    Entonces el mensaje de respuesta debe ser "Bad request, password parameter is missing in DELETE request."
    Y el código de respuesta en el cuerpo debe ser 400
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/common/api_response_schema.json"

  @regression @negative @api:API-12 @issue:12
  Escenario: [API-12] [DELETE /api/deleteAccount] Intentar eliminar una cuenta con credenciales inválidas
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor intenta eliminar una cuenta con credenciales inválidas
    Entonces el código de respuesta en el cuerpo no debe ser 200
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/common/api_response_schema.json"

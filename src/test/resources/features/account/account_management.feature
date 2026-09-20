# language: es
@account @users
Característica: Gestión del Ciclo de Vida de Cuentas de Usuario
  Como consumidor de la API de Automation Exercise
  Quiero gestionar cuentas de usuario mediante los endpoints /api/createAccount, /api/deleteAccount, /api/updateAccount y /api/getUserDetailByEmail
  Para asegurar el registro, consulta de detalles, actualización y eliminación de cuentas

  @smoke @regression @api11
  Escenario: Crear una cuenta de usuario exitosamente con datos válidos
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor envía una solicitud para crear una nueva cuenta con datos válidos
    Entonces el mensaje de respuesta debe ser "User created!"
    Y el código de respuesta en el cuerpo debe ser 201

  @smoke @regression @api14 @requires_user
  Escenario: Consultar los detalles del perfil de un usuario existente por email
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor consulta los detalles del usuario con email "transunion.automation.test@gmail.com"
    Entonces el código de estado de la respuesta debe ser 200
    Y el código de respuesta en el cuerpo debe ser 200
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/account/user_detail_schema.json"

  @regression @api13 @requires_user
  Escenario: Actualizar los datos del perfil de una cuenta existente
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor actualiza los datos del perfil para el usuario "transunion.automation.test@gmail.com" con nombre "TransUnion Automation Updated"
    Entonces el mensaje de respuesta debe ser "User updated!"
    Y el código de respuesta en el cuerpo debe ser 200

  @regression @api12
  Escenario: Eliminar una cuenta de usuario creada con credenciales válidas
    Dado que el actor está listo para consumir la API de Automation Exercise
    Y el actor crea una cuenta de usuario dinámica para eliminación
    Cuando el actor elimina la cuenta de usuario creada
    Entonces el mensaje de respuesta debe ser "Account deleted!"
    Y el código de respuesta en el cuerpo debe ser 200

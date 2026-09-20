# language: es
@auth @login
Característica: Verificación de Login de Usuario
  Como consumidor de la API
  Quiero verificar credenciales de usuario a través del endpoint /api/verifyLogin
  Para confirmar acceso válido, detectar parámetros faltantes, métodos no soportados y usuarios inexistentes

  @smoke @regression @api7
  Escenario: Verificar login exitosamente con credenciales válidas registradas
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor verifica el login con email "transunion.automation.test@gmail.com" y contraseña "Test@Automation2024"
    Entonces el mensaje de respuesta debe ser "User exists!"
    Y el código de respuesta en el cuerpo debe ser 200
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/auth/login_response_schema.json"

  @regression @negative @api8
  Escenario: Intentar verificación de login sin proporcionar el parámetro email
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor verifica el login sin proporcionar el email con contraseña "test@123"
    Entonces el mensaje de respuesta debe ser "Bad request, email or password parameter is missing in POST request."
    Y el código de respuesta en el cuerpo debe ser 400

  @regression @negative @api9
  Escenario: Enviar solicitud DELETE al endpoint de verificación de login y recibir respuesta de método no soportado
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor envía una solicitud no soportada "DELETE" al endpoint de verificación de login
    Entonces el mensaje de respuesta debe ser "This request method is not supported."
    Y el código de respuesta en el cuerpo debe ser 405

  @regression @negative @api10
  Escenario: Intentar verificación de login con credenciales inválidas o inexistentes
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor verifica el login con email "invalido@noexiste.com" y contraseña "claveIncorrecta123"
    Entonces el mensaje de respuesta debe ser "User not found!"
    Y el código de respuesta en el cuerpo debe ser 404

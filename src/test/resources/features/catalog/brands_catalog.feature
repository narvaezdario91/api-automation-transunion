# language: es
@catalog @brands
Característica: Automatización de API para Catálogo de Marcas
  Como consumidor de la API
  Quiero consultar el listado de marcas del catálogo
  Para validar las marcas disponibles, cumplimiento del esquema y manejo de operaciones no permitidas

  @smoke @regression @contract @api:API-03 @issue:3
  Escenario: [API-03] [GET /api/brandsList] Consultar exitosamente todas las marcas y validar el contrato de esquema JSON
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor consulta la lista completa de marcas
    Entonces el código de estado de la respuesta debe ser 200
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/catalog/brands_list_schema.json"
    Y el catálogo debe contener marcas con identificadores válidos

  @regression @negative @api:API-04 @issue:4
  Escenario: [API-04] [PUT /api/brandsList] Intentar método PUT en el endpoint de lista de marcas y recibir respuesta de método no soportado
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor envía una solicitud no soportada "PUT" al endpoint de lista de marcas
    Entonces el mensaje de respuesta debe ser "This request method is not supported."
    Y el código de respuesta en el cuerpo debe ser 405
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/common/api_response_schema.json"

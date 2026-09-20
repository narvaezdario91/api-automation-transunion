# language: es
@catalog @products
Característica: Automatización de API para Catálogo de Productos
  Como consumidor de la API
  Quiero interactuar con los endpoints del catálogo de productos
  Para consultar el catálogo completo, validar contratos de esquema y verificar restricciones de métodos no permitidos

  @smoke @regression @contract
  Escenario: Consultar exitosamente todos los productos y validar el contrato de esquema JSON
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor consulta la lista completa de productos
    Entonces el código de estado de la respuesta debe ser 200
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/catalog/products_list_schema.json"
    Y el catálogo debe contener productos con detalles válidos

  @regression @negative
  Escenario: Intentar método POST en el endpoint de lista de productos y recibir respuesta de método no soportado
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor envía una solicitud no soportada "POST" al endpoint de lista de productos
    Entonces el mensaje de respuesta debe ser "This request method is not supported."
    Y el código de respuesta en el cuerpo debe ser 405

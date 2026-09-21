# language: es
@catalog @search
Característica: Automatización de API para Búsqueda de Productos
  Como consumidor de la API
  Quiero buscar productos utilizando palabras clave
  Para filtrar artículos del catálogo y recibir errores descriptivos cuando faltan criterios de búsqueda

  @smoke @regression @api:API-05 @issue:5
  Esquema del escenario: [API-05] [POST /api/searchProduct] Buscar productos exitosamente con una palabra clave válida
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor busca productos con la palabra clave "<palabra_clave>"
    Entonces el código de estado de la respuesta debe ser 200
    Y el cuerpo de la respuesta debe coincidir con el esquema JSON "schemas/catalog/products_list_schema.json"
    Y todos los productos devueltos deben coincidir con el criterio de búsqueda para "<palabra_clave>"

    Ejemplos:
      | palabra_clave |
      | top           |
      | tshirt        |
      | jean          |

  @regression @negative @api:API-06 @issue:6
  Escenario: [API-06] [POST /api/searchProduct] Buscar productos sin proporcionar el parámetro search_product
    Dado que el actor está listo para consumir la API de Automation Exercise
    Cuando el actor busca productos sin proporcionar el parámetro de búsqueda
    Entonces el mensaje de respuesta debe ser "Bad request, search_product parameter is missing in POST request."
    Y el código de respuesta en el cuerpo debe ser 400

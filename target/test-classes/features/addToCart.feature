#language: es
Característica: Carrito de compras
  Yo como usuario de DemoApp
  Quiero elegir un producto
  Para agregar al carrito de compras

  @ADD_TO_CART
  Esquema del escenario: Elegir producto y agregar a carrito
    Dado que me encuentro en la aplicación DemoApp
    Cuando ingreso al producto "<producto>"
    Y lo agrego al carrito
    Entonces se debería mostrar el producto en el carrito
    Ejemplos:
      | producto            |
      | Sauce Labs Backpack |
Trabajo de Bootcamp UNAB orientado al desarrollo de una app para una librería online. 
La app debe permitir a los usuarios explorar libros, agregar libros a su carrito de compras, ver detalles de cada libro y navegar entre distintas categorías.
Para navegar entre las distintas categorías se utilizó una barra de navegación con Navigation Component. 
Se desarrollaron cinco fragmentos:
  1. Home, con un mensaje de bienvenida
  2. Librería con uso de Recycler View para revisar los distintos libros disponibles, en este caso se muestran 10 libros con su respectiva portada.
  3. Detalle de libros, donde se pueden ver más detalles del libro, como su precio, autor, entre otros, y poder agregar al carrito de compras. Se puede acceder al carrito desde un botón
  dentro del fragment o a través de la barra de navegación.
  4. Carrito de Compras, que también tiene un Recycler View, para ir mostrando los libros añadidos, con un botones para aumentar o dismimuir la cantidad, así como un botón para eliminar 
  el libro añadido. En el carrito se va totalizando el monto de la compra, actualizando el total según van cambiando los libros añadidos/removidos. Hay un botón de finalizar compra, 
  que por el momento sólo vacía el carrito.
  5. Exit, es un Fragment sólo con un mensaje de despedida, para efectos de lo solicitado en el trabajo no cierra la app.

Si bien hay 5 fragmentos, sólo hay 4 botones de navegación, ya que a "Detalle Libros", sólo se ingresa desde la librería al presionar el botón "Ver Más" de un Libro.

Lo más complejo del trabajo fue que en principio se había construido como Activities, y por una interpretación de la lectura del trabajo se llevó a Navigation Component aunque finalmente
no era necesario.

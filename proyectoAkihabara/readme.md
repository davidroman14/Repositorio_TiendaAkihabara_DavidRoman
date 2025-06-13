AUTOR
--------------------------------------------------------------------------------------------
David Román Martín

DESCRIPCIÓN
--------------------------------------------------------------------------------------------
Este proyecto consiste en el desarrollo completo de una aplicación de gestión para la tienda ficticia Akihabara Market, especializada en productos de cultura otaku. El objetivo fue modernizar el sistema de inventario, pasando de un registro manual a una solución digital robusta, escalable y con integración de inteligencia artificial. El proyecto fue desarrollado en Java puro, con persistencia en MySQL, una interfaz de usuario tanto en consola como en Swing (GUI), e integración con LLMs mediante la API de OpenRouter.

TECNOLOGÍAS USADAS
--------------------------------------------------------------------------------------------
Lenguaje y Entorno
------------------
Java 23 – Lenguaje principal de desarrollo.

Eclipse IDE – Entorno de desarrollo utilizado para programar, compilar y ejecutar la aplicación.

Maven – Herramienta de gestión de dependencias y automatización de builds (estructura del proyecto y pom.xml).

Backend y Conectividad
----------------------
MySQL – Sistema de gestión de base de datos relacional para persistencia de datos (productos y clientes).

JDBC (Java Database Connectivity) – API nativa de Java para conectar con MySQL mediante el driver mysql-connector-j 9.3.0.

Inteligencia Artificial
-----------------------
OpenRouter.ai API – Plataforma intermediaria para acceder a modelos LLM.

Meta LLaMA 4 Maverick (Free) – Modelo LLM utilizado para generar descripciones y sugerencias inteligentes desde la consola.

Gson 2.13.1 – Librería Java para manipular y parsear JSON, usada para enviar/recibir datos desde la API de OpenRouter.

Interfaz de Usuario
-------------------
Java Swing – Biblioteca gráfica de Java utilizada para construir una GUI amigable para la gestión visual de productos y clientes.

Testing
-------
JUnit 5 (Jupiter 5.10.2) – Framework de pruebas unitarias, definido como dependencia para validar funcionalidad en componentes clave (como DAOs o lógica de negocio).

ESTRUCTURA DEL PROYECTO
--------------------------------------------------------------------------------------------
Es un proyecto maven estructurado en varias carptetas separando la interfaz gráfica de la interfaz de comandos, el main desde el que se ejecutará la aplicación, los dao y la conexión con la base de datos, los modelos de los objetos de clientes y productos, el lector de la api key y el servicio con el que interactuaremos con la IA.

proyectoAkihabara/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── config/
│   │   │   │   └── ConfigLoader.java
│   │   │   ├── dao/
│   │   │   │   ├── ClienteDAO.java
│   │   │   │   ├── ClienteDAOInterfaz.java
│   │   │   │   ├── DatabaseConnection.java
│   │   │   │   ├── ProductoDAO.java
│   │   │   │   └── ProductoDAOInterfaz.java
│   │   │   ├── main/
│   │   │   │   ├── MainApp.java
│   │   │   │   └── MainGrafica.java
│   │   │   ├── model/
│   │   │   │   ├── ClienteOtaku.java
│   │   │   │   └── ProductoOtaku.java
│   │   │   ├── service/
│   │   │   │   └── LlmService.java
│   │   │   ├── util/
│   │   │   │   └── setupDatos.java
│   │   │   ├── view/
│   │   │   │   ├── InterfazConsolaClientes.java
│   │   │   │   ├── InterfazConsolaInventario.java
│   │   │   │   └── InterfazMainMenu.java
│   │   │   └── viewInterfaz/
│   │   │       ├── SwingActualizarCliente.java
│   │   │       ├── SwingActualizarProducto.java
│   │   │       ├── SwingAddCliente.java
│   │   │       ├── SwingAddProducto.java
│   │   │       ├── SwingAsistenteProducto.java
│   │   │       ├── SwingConsultarCliente.java
│   │   │       ├── SwingConsultarProducto.java
│   │   │       ├── SwingEliminarCliente.java
│   │   │       ├── SwingEliminarProducto.java
│   │   │       ├── SwingInventarioProducto.java
│   │   │       ├── SwingListaClientes.java
│   │   │       ├── SwingMenuClientes.java
│   │   │       ├── SwingMenuPrincipal.java
│   │   │       └── SwingMenuProducto.java
│   ├── resources/
├── test/
│   ├── java/
│   └── resources/
├── target/
├── pom.xml


CONFIGURACIÓN DEL PROYECTO
--------------------------------------------------------------------------------------------
1. Ejecutaremos el script SQL que creará la base de datos y el usuario que manejará la misma

2. Crearemos en la raíz del proyecto un archivo llamado "config.properties" en el que pondremos unicamente la api key para poder usar el asistente IA, para consguir la api key lo haremos desde la siguiente página: https://openrouter.ai/

EJECUCIÓN DEL PROYECTO
--------------------------------------------------------------------------------------------
Para ejecutar el proyecto lo podemos hacer de las siguientes formas:

Mediante la interfaz gráfica: Ejecutamos el archivo tiendaAkihabraInterfaz.jar teniendo en la misma carpeta el archivo config.properties y después de haber ejecutado el script sql.

Mediante la interfaz de comandos: Ejecutamos el archivo ejecutableComandos.bat teniendo en la misma carpeta el archivo tiendaAkihabraComandos.jar, el archivo config.properties y después de haber ejecutado el script sql.

FUNCIONALIDADES IMPLEMENTADAS
--------------------------------------------------------------------------------------------
CRUD de productos (Agregar, Consultar, Editar, Eliminar)
CRUD de clientes (Agregar, Consultar, Editar, Eliminar)
Listado completo de productos/clientes
Generación de descripciones de producto con IA
Sugerencia automática de categoría para productos con IA
Interfaz en consola y GUI (Swing)
Persistencia total en base de datos MySQL
Validación de datos de entrada y manejo de errores
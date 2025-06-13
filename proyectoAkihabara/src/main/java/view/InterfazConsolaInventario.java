package view;

import java.util.List;
import java.util.Scanner;

import dao.DatabaseConnection;
import dao.ProductoDAO;
import model.ProductoOtaku;
import service.LlmService;

/**
 * Clase que contiene las funciones con las que interactuará el usuario
 * en la aplicación para gestionar productos.
 * 
 * @author David Román
 * @version 1.0
 */

public class InterfazConsolaInventario {
	static DatabaseConnection conexion = new DatabaseConnection();
	static ProductoDAO dao = new ProductoDAO(conexion);
	
	/**
	 * Muestra el menú principal de opciones por consola y solicita al usuario que seleccione una.
	 *
	 * @return La opción seleccionada por el usuario.
	 */
	public static void menuInventario() {
		int opcmenu = 0;
		while (opcmenu != 7) {
			System.out.println("\n------------ MENÚ INVENTARIO ------------");
			System.out.println("1. Añadir producto al inventario.");
			System.out.println("2. Consultar producto especifico por su ID.");
			System.out.println("3. Ver todos los productos del inventario.");
			System.out.println("4. Actualizar un producto ya existente.");
			System.out.println("5. Eliminar un producto del inventario.");
			System.out.println("6. Utilizar asistente IA.");
			System.out.println("7. Volver al menú.");
			opcmenu = pedirEntero("Seleccione una opción: ");
			switch (opcmenu) {
				// Si es uno agregaremos un nuevo producto
				case 1:
					System.out.println("\n--------- AGREAGAR NUEVO PRODUCTO ---------");
					ProductoOtaku productoadd = InterfazConsolaInventario.crearProducto();
					dao.agregarProducto(productoadd);
					System.out.println("PRODUCTO AÑADIDO CORRECTAMENTE");
					break;
				// Mostrará el producto por la pantalla
				case 2: 
					System.out.println("\nCONSULTAR PRODUCTO POR ID");
					int idbusc = InterfazConsolaInventario.pedirEntero("Ingrese la id del producto que desea buscar: ");
					ProductoOtaku productobuscar = dao.obtenerProductoId(idbusc);
					InterfazConsolaInventario.mostrarProducto(productobuscar);
					break;
				// Mostrará todo el inventario por pantalla
				case 3:
					System.out.println("\nMOSTRAR INVENTARIO COMPLETO");
					List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();
					InterfazConsolaInventario.mostrarListaProductos(productos);
					break;
				// Nos mostrará el submenú para actualizar un producto
				case 4:
					System.out.println("\n------------ ACTUALIZAR PRODUCTO ------------");
					int idact = InterfazConsolaInventario.pedirEntero("Ingrese la id del producto que quiere actualizar: ");
					ProductoOtaku productobuscaract = dao.obtenerProductoId(idact);
					ProductoOtaku productoact = InterfazConsolaInventario.actualizarProducto(productobuscaract);
					boolean confirmacionact = dao.actualizarProducto(productoact);
					InterfazConsolaInventario.mostrarProducto(productoact);
					break;
				// Nos pedirá una id y eliminaremos un producto
				case 5:
					System.out.println("\n----------- ELIMINAR UN PRODUCTO -----------");
					int idelim = InterfazConsolaInventario.pedirEntero("Ingrese la id del producto que quiere eliminar: ");
					boolean confirmareliminacion = dao.eliminarProducto(idelim);
					InterfazConsolaInventario.eliminarProducto(confirmareliminacion);
					break;
				// Nos mostrará el submenú con el asistente de IA
				case 6:
					System.out.println("\n--------------- ASISTENTE IA ---------------");
					InterfazConsolaInventario.consultaIA();
					break;
				// Opción para salir
				case 7:
					System.out.println("\nVOLVIENDO AL MENÚ PRINCIPAL");
					conexion.cerrarConexion();
					break;
				// Mensaje por defecto en caso de ingresar un número fuera del menú
				default:
					System.out.println("Ingrese una opción valida.");
					break;
			}
		}
	}
	
	/**
	 * Muestra la información de un producto específico en formato tabular.
	 *
	 * @param producto El producto que se desea mostrar
	 */
	public static ProductoOtaku crearProducto() {
		// Controlamos que el nombre no está vacío.
		String nombre;
		do {
			nombre = pedirString("Ingrese el nombre del producto: ");
			if (nombre.trim().isEmpty()) {
				System.out.println("El nombre no puede estar vacío.");
			}
		} while (nombre.trim().isEmpty());
		// Controlamos que la categoría no esté vacía
		String categoria;
		do {
			categoria = pedirString("Ingrese la categoría del producto: ");
			if (categoria.trim().isEmpty()) {
				System.out.println("La categoría no puede estar vacía.");
			}
		} while (categoria.trim().isEmpty());
		// Controlamos que el precio sea mayor que 0
		double precio = 0;
		do {
			precio = pedirDecimal("Ingrese el precio del producto: ");
			if (precio <= 0) {
				System.out.println("El precio no puede ser menos que 0.");
			}
		} while (precio <= 0);
		// Controlamos que el stock no sea menor que 0
		int stock = 0;
		do {
			stock = pedirEntero("Ingrese el stock del producto: ");
			if(stock == 0) {
				System.out.println("El stock no puede ser menos que 0.");
			}
		} while(stock < 0);
		// Creamos el producto y lo devolvemos
		ProductoOtaku producto = new ProductoOtaku(nombre, categoria, precio, stock);
		return producto;
	}
	
	/**
	 * Muestra la información de un producto específico en formato tabular.
	 *
	 * @param producto El producto que se desea mostrar.
	 */
	public static void mostrarProducto(ProductoOtaku producto) {
		// Si la id es 0 el producto no existe por lo que mostraremos un mensaje por pantalla
		if(producto.getId() == 0) {
			System.out.println("NO EXISTE EL PRODUCTO, COMPRUEBE LA ID.");
		// Si existe mostraremos el prodcto por pantalla
		} else {
			System.out.println("\n------------------------------- PRODUCTO -------------------------------");
			System.out.printf("%-5s %-25s %-15s %-15s %-15s\n","ID","NOMBRE","CATEGORIA","PRECIO","STOCK");
			System.out.printf("%-5s %-25s %-15s %-15s %-15s\n",producto.getId(),producto.getNombre()
					,producto.getCategoria(),producto.getPrecio(),producto.getStock());
		}
	}
	
	/**
	 * Muestra la lista completa de productos disponibles en el inventario.
	 *
	 * @param inventario Lista de productos a mostrar.
	 */
	public static void mostrarListaProductos(List<ProductoOtaku> inventario) {
		// Si el inventario está vacío lo indicaremos por pantalla
		if (inventario.isEmpty()) {
			System.out.println("NO HAY PRODUCTOS EN EL INVENTARIO.");
		// Si no está vacío mostraremos la lista por pantalla
		} else {
			System.out.println("\n-------------------------- LISTA DE PRODUCTOS --------------------------");
			System.out.printf("%-5s %-25s %-15s %-15s %-15s\n","ID","NOMBRE","CATEGORIA","PRECIO","STOCK");
			for (ProductoOtaku p : inventario) {
				System.out.printf("%-5s %-25s %-15s %-15s %-15s\n",p.getId(),p.getNombre()
						,p.getCategoria(),p.getPrecio(),p.getStock());
			}
		}
	}
	
	/**
	 * Permite al usuario actualizar uno de los campos de un producto existente.
	 * Muestra un submenú con las opciones editables (nombre, categoría, precio, stock).
	 *
	 * @param producto El producto que se desea actualizar.
	 * @return El producto con los datos actualizados.
	 */
	public static ProductoOtaku actualizarProducto(ProductoOtaku producto) {
		// Obtenemos el producto usando un get para la id de la base de datos si existe nos
		// mostrará un submenú en el que seleccionaremos que queremos actualizar del producto
		ProductoOtaku comprobacion = dao.obtenerProductoId(producto.getId());
		if (comprobacion.getId() != 0) {
			System.out.println("1. Actualizar nombre.");
			System.out.println("2. Actualizar categoria.");
			System.out.println("3. Actualizar precio.");
			System.out.println("4. Actualizar stock.");
			System.out.println("5. Volver a menú.");
			int opcact = pedirEntero("Seleccione una opción: ");
			switch (opcact) {
				// Controlamos que el nombre no esté vacío
				case 1:
					String nombreact;
					do {
						nombreact = pedirString("Ingrese el nuevo nombre: ");
						if (nombreact.trim().isEmpty()) {
							System.out.println("El nombre no puede estar vacío.");
						}
					} while (nombreact.trim().isEmpty());
					System.out.println("EL NOMBRE SE HA ACTUALIZADO");
					producto.setNombre(nombreact);
					break;
				// Controlamos que la categoría no esté vacía
				case 2:
					String categoriaact;
					do {
						categoriaact = pedirString("Ingrese la nueva categoría: ");
						if (categoriaact.trim().isEmpty()) {
							System.out.println("La categoría no puede estar vacía.");
						}
					} while (categoriaact.trim().isEmpty());
					System.out.println("LA CATEGORÍA SE HA ACTUALIZADO");
					producto.setNombre(categoriaact);
					break;
				// Controlamos que el precio sea mayor que 0
				case 3:
					double precioact = 0;
					do {
						precioact = pedirDecimal("Ingrese el nuevo precio: ");
						if (precioact <= 0) {
							System.out.println("El precio no puede ser menos que 0.");
						}
					} while (precioact <= 0);
					System.out.println("EL PRECIO SE HA ACTUALIZADO");
					producto.setPrecio(precioact);
					break;
				// Controlamos que el stock no sea menor que 0
				case 4:
					int stockact = 0;
					do {
						stockact = pedirEntero("Ingrese el stock del producto: ");
						if(stockact < 0) {
							System.out.println("El stock no puede ser menos que 0.");
						}
					} while(stockact < 0);
					System.out.println("EL STOCK SE HA ACTUALIZADO");
					producto.setStock(stockact);
					break;
				// Volvemos al menú principal
				case 5:
					System.out.println("Volviendo al menú...");
					break;
				// Mensaje por defecto en caso de ingresar un número fuera del menú
				default:
					System.out.println("Ingrese una opción valida.");
					break;
			}	
		}
		// Devolvemos el producto con el campo seleccionado actualizado
		return producto;
	}
	
	/**
	 * Muestra un mensaje de confirmación al eliminar un producto
	 * 
	 * @param confirmacion Booleano enviado al eliminar un producto
	 */
	public static void eliminarProducto(boolean confirmacion) {
		if (confirmacion) {
			System.out.println("EL PRODUCTO SE HA ELIMINADO CORRECTAMENTE.");
		} else {
			System.out.println("NO SE HA ELIMINADO NINGÚN PRODUCTO.");
		}
	}
	
	/**
	 * Permite interactuar con una IA para generar descripciones de productos o sugerencias de categorías.
	 * Muestra un submenú con las opciones disponibles.
	 */
	public static void consultaIA() {
		// Mostramos un submenú con las opciones qe tendremos a la hora de interactuar con la IA
		System.out.println("1. Generar descripción de producto.");
		System.out.println("2. Sugerir categoría para producto.");
		System.out.println("3. Volver al menú.");
		int opcconsulta = pedirEntero("Seleccione una opción: ");
		switch (opcconsulta) {
		// Si seleccionamos la primera opción ingresaremos la id de un producto
		case 1:
			System.out.println("\n------------ GENERAR DESCRIPCIÓN -----------");
			int iddescia = pedirEntero("Ingrese la id del producto: ");
			ProductoOtaku productodesc = dao.obtenerProductoId(iddescia);
			// Si la id es 0 el producto no existe, imprimiremos por pantalla un mensaje indicandolo
			if (productodesc.getId() == 0) {
				System.out.println("NO EXISTE EL PRODUCTO, COMPRUEBE LA ID.");
			// Si el producto existe imprimiremos por pantalla la descripción sugerida por la ia
			} else {
				String nombreprod = productodesc.getNombre();
				String respuestadesc = LlmService.enviarPrompt(
						"Genera una descripción de marketing breve y atractiva para el producto otaku:"+
						nombreprod + ", en español porfavor no me des varias opciones, que sea solo una descripción."
						);
				if (respuestadesc == null) {
					respuestadesc = "Ha habido un problema al enviar el mensaje.";
				} else {
					System.out.println("Descripción para: "+nombreprod);
				}
				System.out.println(respuestadesc);
				break;
			}
			break;
		// Si seleccionamos la segunda opción nos pedirá que ingresemos el nombre de un nuevo producto y imprimirá por pantalla
		// una categoría sugerida para el mismo.
		case 2:
			System.out.println("\n--------- CATEGORIA NUEVO PRODUCTO ---------");
			String nombreprodcat = pedirString("Ingresa en nombre del producto: ");
			String respuestacategoria = LlmService.enviarPrompt("Para un producto otaku llamado" + 
					nombreprodcat
					+ ", sugiere una categoría adecuada de esta lista: Figura, Manga, Póster, Llavero, Ropa,Videojuego, Otro. Que la respuesta sea solo la categoria"
					+ "no des ninguna descripción ni nada, añade algo delante de la categoria del esilo: te sugiero la categoria de [categoria que cuadre mejor]");
			System.out.println("Categoria sugerida:");
			System.out.println(respuestacategoria);
			break;
		// Mensaje por defecto en caso de ingresar un número fuera del menú.
		default:
			System.out.println("\nIngrese una opción valida.");
			break;
		}	
	}
	
	// Abrimos el scanner
	private static final Scanner scan = new Scanner(System.in);
	
	/**
	 * Función para pedir un número entero y controlar errores
	 * 
	 * @param mensaje Mensaje que se le mostrará al usuario por pantalla cuando se use la función
	 * @return Devuelve el número entero introducido por el usuario
	 */
    public static int pedirEntero(String mensaje) {
        int valor = 0;
        boolean error;
        do {
            error = false;
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scan.next());
            } catch (Exception e) {
                System.out.println("[ERROR] Valor incorrecto");
                error = true;
                scan.nextLine();
            }
        } while (error);
        return valor;
    }
    
    /**
	 * Función para pedir un número decimal y controlar errores
	 * 
	 * @param mensaje Mensaje que se le mostrará al usuario por pantalla cuando se use la función
	 * @return Devuelve el número decimal introducido por el usuario
	 */
    public static double pedirDecimal(String mensaje) {
        double valor = 0;
        boolean error;
        do {
            error = false;
            System.out.print(mensaje);
            try {
            	valor = Double.parseDouble(scan.next());
            } catch (Exception e) {
                System.out.println("[ERROR] Valor incorrecto");
                error = true;
                scan.nextLine();
            }
        } while (error);
        return valor;
    }
    
    /**
	 * Función para pedir un String y controlar errores
	 * 
	 * @param mensaje Mensaje que se le mostrará al usuario por pantalla cuando se use la función
	 * @return Devuelve el String introducido por el usuario
	 */
    public static String pedirString(String mensaje) {
		Scanner scan = new Scanner(System.in);
		String valor=null;
		boolean error = false;
		do {
			error = false;
			System.out.print(mensaje);
		try {
			valor = scan.nextLine();
		}catch (Exception e) {
			System.out.println("Valor incorrecto");
			error = true;
			}
		}while(error);
		return valor;
		}
    
    /**
	 * Función para pedir un booleano y controlar errores
	 * 
	 * @param mensaje Mensaje que se le mostrará al usuario por pantalla cuando se use la función
	 * @return Devuelve el booleano introducido por el usuario
	 */
    public static boolean pedirBoolean(String mensaje) {
        Scanner scan = new Scanner(System.in);
        boolean valor = false;
        boolean error;
        do {
            error = false;
            System.out.print(mensaje);
            String input = scan.nextLine().trim().toLowerCase();
            if (input.equals("si")) {
                valor = true;
            } else if (input.equals("no")){
            	valor = false;
            } else {
            	System.out.println("Ingresa 'true' o 'false'");
                error = true;
            }
        } while (error);
        return valor;
    }
}

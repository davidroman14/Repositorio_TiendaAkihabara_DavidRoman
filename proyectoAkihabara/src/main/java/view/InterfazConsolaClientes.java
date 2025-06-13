package view;

import java.util.List;
import java.util.Scanner;
import dao.ClienteDAO;
import dao.DatabaseConnection;
import model.ClienteOtaku;
/**
 * Clase que contiene las funciones con las que interactuará el usuario
 * en la aplicación para gestionar clientes.
 * 
 * @author David Román
 * @version 1.0
 */
public class InterfazConsolaClientes {
	static DatabaseConnection conexion = new DatabaseConnection();
	static ClienteDAO daoc = new ClienteDAO(conexion);
	
	/**
	 * Muestra el menú de gestión de clientes por pantalla.
	 *
	 */
	public static void menuClientes() {
		int opcmenu = 0;
		while (opcmenu != 6) {
			System.out.println("\n------------ MENÚ CLIENTES ------------");
			System.out.println("1. Añadir nuevo cliente.");
			System.out.println("2. Consultar cliente por ID.");
			System.out.println("3. Ver todos los clientes registrados.");
			System.out.println("4. Actualizar datos de un cliente.");
			System.out.println("5. Eliminar un cliente del sistema.");
			System.out.println("6. Volver al menú.");
			opcmenu = pedirEntero("Seleccione una opción: ");
			switch (opcmenu) {
				// Si es uno agregaremos un nuevo cliente
				case 1:
					System.out.println("\n--------- AGREAGAR NUEVO CLIENTE ---------");
					ClienteOtaku clienteadd = crearCliente();
					boolean confirmaraddcli = daoc.agregarCliente(clienteadd);
					if (confirmaraddcli) {
						System.out.println("EL CLIENTE SE HA AGREGADO CORRECTAMENTE.");
					} else {
						System.out.println("EL CLIENTE NO SE HA AGREGADO.");
					}
					break;
				// Mostrará el cliente por la pantalla
				case 2: 
					System.out.println("\nCONSULTAR CLIENTE POR ID");
					int idbusccli = InterfazConsolaInventario.pedirEntero("Ingrese la id del cliente que desea buscar: ");
					ClienteOtaku clibuscar = daoc.obtenerClientePorId(idbusccli);
					mostrarCliente(clibuscar);
					break;
				// Mostrará todos los clientes registrados
				case 3:
					System.out.println("\nMOSTRAR TODOS LOS CLIENES");
					
					List<ClienteOtaku> clientes = daoc.obtenerTodosLosClientes();
					mostrarListaClientes(clientes);
					break;
				// Nos mostrará el submenú para actualizar un cliente
				case 4:
					System.out.println("\n------------ ACTUALIZAR CLIENTE ------------");
					int idact = InterfazConsolaInventario.pedirEntero("Ingrese la id del cliente que quiere actualizar: ");
					
					ClienteOtaku clibuscact = daoc.obtenerClientePorId(idact);
					ClienteOtaku cliact = actualizarCliente(clibuscact);
					boolean confirmacionact = daoc.actualizarCliente(cliact);
					mostrarCliente(cliact);
					if (confirmacionact) {
						System.out.println("EL CLIENTE SE HA ACTUALIZADO CORRECTAMENTE");
					} else {
						System.out.println("EL CLIENTE NO SE HA ACTUALIZADO CORRECTAMENTE");
					}
					break;
				// Nos pedirá una id y eliminaremos un cliente
				case 5:
					System.out.println("\n----------- ELIMINAR UN CLIENTE -----------");
					int idclielim = InterfazConsolaInventario.pedirEntero("Ingrese la id del cliente que quiere eliminar: ");
					boolean confirmareliminacioncli = daoc.eliminarCliente(idclielim);
					eliminarCliente(confirmareliminacioncli);
					break;
				// Opción para salir al menú principal
				case 6:
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
	 * Muestra un formulario con la información del nuevo cliente.
	 *
	 */
	public static ClienteOtaku crearCliente() {
		// Controlamos que el nombre no está vacío.
		String nombre;
		do {
			nombre = pedirString("Ingrese el nombre del cliente: ");
			if (nombre.trim().isEmpty()) {
				System.out.println("El nombre no puede estar vacío.");
			}
		} while (nombre.trim().isEmpty());
		// Controlamos que el email no esté vacío
		String email;
		do {
			email = pedirString("Ingrese el correo electronico del cliente: ");
			if (email.trim().isEmpty()) {
				System.out.println("La correo no puede estar vacío.");
			}
		} while (email.trim().isEmpty());
		// Controlamos que el teléfono no esté vacío
		String telefono;
		do {
			telefono = pedirString("Ingrese el correo teléfono del cliente: ");
			if (telefono.trim().isEmpty()) {
				System.out.println("La correo no puede estar vacío.");
			}
		} while (telefono.trim().isEmpty());
		// Creamos el cliente y lo devolvemos
		ClienteOtaku cliente = new ClienteOtaku(nombre, email, telefono);
		return cliente;
	}
	
	/**
	 * Muestra la información de un cliente especifico en una tabla.
	 *
	 * @param cliente El cliente que se desea mostrar.
	 */
	public static void mostrarCliente(ClienteOtaku cliente) {
		// Si la id es 0 el cliente no existe por lo que mostraremos un mensaje por pantalla
		if(cliente.getId() == 0) {
			System.out.println("NO EXISTE EL PRODUCTO, COMPRUEBE LA ID.");
		// Si existe mostraremos el cliente por pantalla
		} else {
			System.out.println("\n--------------------------------------------------- CLIENTE ---------------------------------------------------");
			System.out.printf("%-5s %-25s %-15s %-15s %-20s\n","ID","NOMBRE","EMAIL","TELÉFONO","FECHA DE REGISTRO");
			System.out.printf("%-5s %-25s %-15s %-15s %-20s\n",cliente.getId(),cliente.getNombre(),cliente.getEmail(),cliente.getTelefono(),cliente.getFecha_registro());
		}
	}
	
	/**
	 * Muestra la lista completa de clientes registrados.
	 *
	 * @param clientes Lista de clientes a mostrar.
	 */
	public static void mostrarListaClientes(List<ClienteOtaku> clientes) {
		// Si la lista de clientes registrados está vacía lo mostraremos por pantalla
		if (clientes.isEmpty()) {
			System.out.println("NO HAY CLIENTES REGISTRADOS.");
		// Si no está vacía mostraremos la lista de clientes por pantalla
		} else {
			System.out.println("\n-------------------------------------------- LISTA DE CLIENTES -------------------------------------------");
			System.out.printf("%-5s %-25s %-40s %-15s %-20s\n","ID","NOMBRE","EMAIL","TELÉFONO","FECHA DE REGISTRO");
			for (ClienteOtaku c : clientes) {
				System.out.printf("%-5s %-25s %-40s %-15s %-20s\n",c.getId(),c.getNombre(),c.getEmail(),c.getTelefono(),c.getFecha_registro());
			}
		}
	}
	
	/**
	 * Permite al usuario actualizar uno de los campos de un cliente existente.
	 * Muestra un submenú con las opciones editables (nombre, email, teléfono).
	 *
	 * @param cliente El cliente que se desea actualizar.
	 * @return El cliente con los datos actualizados.
	 */
	public static ClienteOtaku actualizarCliente(ClienteOtaku cliente) {
		// Obtenemos el cliente usando un get para la id de la base de datos si existe nos
		// mostrará un submenú en el que seleccionaremos que queremos actualizar del mismo
		ClienteOtaku comprobacion = daoc.obtenerClientePorId(cliente.getId());
		if (comprobacion.getId() != 0) {
			System.out.println("1. Actualizar nombre.");
			System.out.println("2. Actualizar Email.");
			System.out.println("3. Actualizar Teléfono.");
			System.out.println("4. Volver a menú.");
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
					cliente.setNombre(nombreact);
					break;
				// Controlamos que el email no esté vacío
				case 2:
					String emailact;
					do {
						emailact = pedirString("Ingrese el nuevo correo electronico: ");
						if (emailact.trim().isEmpty()) {
							System.out.println("El email no puede estar vacío.");
						}
					} while (emailact.trim().isEmpty());
					System.out.println("EL EMAIL SE HA ACTUALIZADO");
					cliente.setEmail(emailact);
					break;
				// Controlamos que el teléfono no esté vacío
				case 3:
					String tlfact;
					do {
						tlfact = pedirString("Ingrese el nuevo correo electronico: ");
						if (tlfact.trim().isEmpty()) {
							System.out.println("El email no puede estar vacío.");
						}
					} while (tlfact.trim().isEmpty());
					System.out.println("EL EMAIL SE HA ACTUALIZADO");
					cliente.setTelefono(tlfact);
					break;
				// Volvemos al menú de gestión de clientes
				case 4:
					System.out.println("Volviendo al menú...");
					break;
				// Mensaje por defecto en caso de ingresar un número fuera del menú
				default:
					System.out.println("Ingrese una opción valida.");
					break;
			}	
		}
		// Devolvemos el cliente con el campo seleccionado actualizado
		return cliente;
	}
	
	/**
	 * Muestra un mensaje de confirmación al eliminar un cliente
	 * 
	 * @param confirmacion Booleano enviado al eliminar un cliente
	 */
	public static void eliminarCliente(boolean confirmacion) {
		if (confirmacion) {
			System.out.println("EL CLIENTE SE HA ELIMINADO CORRECTAMENTE.");
		} else {
			System.out.println("NO SE HA ELIMINADO NINGÚN CLIENTE.");
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
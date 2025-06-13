package view;
/**
 * Interfaz con el menú principal de la aplicación mediante comandos.
 * 
 * @author David Román
 * @version 1.0
 */
public class InterfazMainMenu {
	public static void mainMenu() {
		int opcmenu = 0;
		do {
			System.out.println("MENU PRINCIPAL");
			System.out.println("1. Gestionar Inventario.");
			System.out.println("2. Gestionar Clientes");
			System.out.println("3. Salir");
			opcmenu = InterfazConsolaClientes.pedirEntero("Seleccione una opción: ");
			switch (opcmenu) {
			case 1:
				InterfazConsolaInventario.menuInventario();
				break;
			case 2:
				InterfazConsolaClientes.menuClientes();
				break;
			case 3:	
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Seleccione una opción valida.");
				break;
			}
		} while (opcmenu != 3);
	}
}

package util;

import dao.DatabaseConnection;
import dao.ProductoDAO;
import model.ProductoOtaku;
import view.InterfazConsolaInventario;

public class setupDatos {
	public static void main(String[] args) {
		DatabaseConnection conexion = new DatabaseConnection();
		ProductoDAO dao = new ProductoDAO(conexion);
		InterfazConsolaInventario vista = new InterfazConsolaInventario();
		
        ProductoOtaku p1 = new ProductoOtaku("Figura Goku", "Figuras", 25.99, 10);
        ProductoOtaku p2 = new ProductoOtaku("Camiseta Naruto", "Ropa", 15.50, 5);
        ProductoOtaku p3 = new ProductoOtaku("Manga One Piece Vol.1", "Manga", 9.99, 20);
        ProductoOtaku p4 = new ProductoOtaku("Llavero Totoro", "Llavero", 4.99, 50);
        ProductoOtaku p5 = new ProductoOtaku("Póster Attack on Titan", "Póster", 6.75, 30);
        ProductoOtaku p6 = new ProductoOtaku("Videojuego Dragon Ball Z", "Videojuego", 39.99, 8);
        ProductoOtaku p7 = new ProductoOtaku("Sudadera One Punch Man", "Ropa", 29.95, 12);
        ProductoOtaku p8 = new ProductoOtaku("Figura Naruto", "Figura", 27.50, 7);
        ProductoOtaku p9 = new ProductoOtaku("Manga Death Note Vol.1", "Manga", 8.50, 15);
        ProductoOtaku p10 = new ProductoOtaku("Peluca de Gintoki", "Otro", 18.00, 3);
        dao.agregarProducto(p4);
        dao.agregarProducto(p5);
        dao.agregarProducto(p6);
        dao.agregarProducto(p7);
        dao.agregarProducto(p8);
        dao.agregarProducto(p9);
        dao.agregarProducto(p10);
        dao.agregarProducto(p1);
        dao.agregarProducto(p2);
        dao.agregarProducto(p3);       
        /*
        System.out.println("");
        ProductoOtaku pbuscar = dao.obtenerProductoId(2);
        vista.mostrarProducto(pbuscar);

        List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();
        vista.mostrarListaProductos(productos);
        
        System.out.println("");
        boolean eliminar = dao.eliminarProducto(1);
        vista.eliminarProducto(eliminar);
        
        System.out.println("");
        ProductoOtaku pact = new ProductoOtaku(1,"HOLA", "Manga", 9.99, 20);
        boolean actualizar = dao.actualizarProducto(pact);
        vista.actualizarProducto(actualizar);
        
        ProductoOtaku pbuscar2 = dao.obtenerProductoId(3);
        vista.mostrarProducto(pbuscar2);
        
        List<ProductoOtaku> productos2 = dao.buscarProductosPorNombre("Camiseta Naruto");
        vista.mostrarListaProductos(productos2);
        */
	}
}

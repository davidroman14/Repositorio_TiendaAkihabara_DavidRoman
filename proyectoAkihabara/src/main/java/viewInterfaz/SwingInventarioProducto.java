package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import dao.DatabaseConnection;
import dao.ProductoDAO;
import model.ProductoOtaku;
import java.awt.Font;
/**
 * Clase que usa jframe para mostrar el inventario
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingInventarioProducto {
	
	static DatabaseConnection conexion = new DatabaseConnection();
	static ProductoDAO dao = new ProductoDAO(conexion);
	/**
	 * Función que mostrará todos los prductos del inventario
	 */
	public static void mostrarInventarioSwing() {
	    JFrame frame = new JFrame("Inventario Tienda Akihabara");
	    frame.setResizable(false);

	    JLabel titulo = new JLabel("Inventario Tienda Akihabara");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(166, 29, 253, 30);
	    frame.getContentPane().add(titulo);

	    List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();
	    String[] columnas = {"ID", "Nombre", "Categoría", "Precio", "Stock"};

	    String[][] datos = new String[productos.size()][5];
	    for (int i = 0; i < productos.size(); i++) {
	        ProductoOtaku p = productos.get(i);
	        datos[i][0] = String.valueOf(p.getId());
	        datos[i][1] = p.getNombre();
	        datos[i][2] = p.getCategoria();
	        datos[i][3] = String.valueOf(p.getPrecio());
	        datos[i][4] = String.valueOf(p.getStock());
	    }

	    JTable tabla = new JTable(datos, columnas);
	    JScrollPane scrollPane = new JScrollPane(tabla);
	    scrollPane.setBounds(30, 70, 520, 300);
	    frame.getContentPane().add(scrollPane);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(200, 400, 200, 40);
	    frame.getContentPane().add(volver);

	    volver.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            SwingMenuProducto.menuSwing();
	            frame.dispose();
	        }
	    });

	    frame.setSize(600, 500);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	
}

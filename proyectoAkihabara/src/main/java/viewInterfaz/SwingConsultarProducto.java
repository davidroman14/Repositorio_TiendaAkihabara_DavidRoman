package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import dao.DatabaseConnection;
import dao.ProductoDAO;
import model.ProductoOtaku;
import java.awt.Font;
/**
 * Clase que usa jframe para consultar un producto por id
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingConsultarProducto {
	
	static DatabaseConnection conexion = new DatabaseConnection();
	static ProductoDAO dao = new ProductoDAO(conexion);
	
	/**
	 * Función en la que ingresaremos una id y nos mostrará toda la información del producto
	 */
	public static void consultarIDSwing() {
		JFrame frame = new JFrame("Producto Inventario Akihabara");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("ID del producto");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(200, 11, 138, 50);
	    frame.getContentPane().add(titulo);
	    
	    JLabel IDProd = new JLabel("ID del Producto:");
	    IDProd.setBounds(50, 80, 150, 25);
	    JTextField IDBuscar = new JTextField();
	    IDBuscar.setBounds(200, 80, 300, 25);
	    frame.getContentPane().add(IDProd);
	    frame.getContentPane().add(IDBuscar);    
	    
	    JButton guardar = new JButton("Buscar Producto");
	    guardar.setBounds(98, 145, 180, 40);
	    frame.getContentPane().add(guardar);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(288, 145, 180, 40);
	    frame.getContentPane().add(volver);
	    
	    guardar.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	String IDProd = IDBuscar.getText().trim();
    	    	if (IDProd.isEmpty()){
	            	JOptionPane.showMessageDialog(null, "Rellene la ID por favor.","Error",JOptionPane.ERROR_MESSAGE);
	            } else {
	            	try {
	            		int IDProdNumeric = Integer.parseInt(IDProd);
	            		ProductoOtaku producto = dao.obtenerProductoId(IDProdNumeric);
		            	if (producto.getId() == 0) {
		            		JOptionPane.showMessageDialog(null, "No hay un producto con esta ID.","Error",JOptionPane.ERROR_MESSAGE);
		            	} else {
		            		String[][] datos = new String[1][5];
		            		String[] columnas = {"ID", "Nombre", "Categoría", "Precio", "Stock"};
		            		datos[0][0] = String.valueOf(producto.getId());
		        	        datos[0][1] = producto.getNombre();
		        	        datos[0][2] = producto.getCategoria();
		        	        datos[0][3] = String.valueOf(producto.getPrecio());
		        	        datos[0][4] = String.valueOf(producto.getStock());
		        	        
		        	        JTable tabla = new JTable(datos, columnas);
		        		    JScrollPane scrollPane = new JScrollPane(tabla);
		        		    scrollPane.setBounds(30, 70, 520, 50);
		        		    frame.getContentPane().add(scrollPane);
		            	}
	            	} catch (NumberFormatException e1) {
	            	    JOptionPane.showMessageDialog(null, "La ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
	            	}            	
	            }
    	    }
    	});
	     
	    volver.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	SwingMenuProducto.menuSwing();
    	        frame.dispose();
    	    }
    	});
	          
	    frame.setSize(600, 269);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
}

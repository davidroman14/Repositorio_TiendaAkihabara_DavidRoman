package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import dao.DatabaseConnection;
import dao.ProductoDAO;
import model.ProductoOtaku;
import java.awt.Font;
/**
 * Clase que usa jframe para eliminar un producto
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingEliminarProducto {

	static DatabaseConnection conexion = new DatabaseConnection();
	static ProductoDAO dao = new ProductoDAO(conexion);
	
	/**
	 * Función para ingresar la id del producto que queremos eliminar
	 */
	public static void eliminarProductoSwing() {
		JFrame frame = new JFrame("Eliminar Producto Akihabara");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("ID del Producto a Eliminar");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(167, 19, 233, 50);
	    frame.getContentPane().add(titulo);
	    
	    JLabel IDProd = new JLabel("ID del Producto:");
	    IDProd.setBounds(48, 112, 150, 25);
	    JTextField IDBuscar = new JTextField();
	    IDBuscar.setBounds(198, 112, 300, 25);
	    frame.getContentPane().add(IDProd);
	    frame.getContentPane().add(IDBuscar);    
	    
	    JButton guardar = new JButton("Buscar Producto");
	    guardar.setBounds(100, 186, 180, 40);
	    frame.getContentPane().add(guardar);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(300, 186, 180, 40);
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
		            		dao.eliminarProducto(IDProdNumeric);
		            		productoEliminado();
		            		frame.dispose();
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
	          
	    frame.setSize(600, 314);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	/**
	 * Función que indicará que el producto se ha eliminado
	 */
	public static void productoEliminado() {
		JFrame frame = new JFrame("Producto Eliminado");

	    JLabel titulo = new JLabel("El Producto se ha Eliminado Correctamente");
	    titulo.setBounds(175, 50, 1000, 50);
	    frame.getContentPane().add(titulo);
	    
	    JButton volver = new JButton("Volver");
	     volver.setBounds(50, 500, 470, 50);  
	     frame.getContentPane().add(volver);
	     
	     volver.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingMenuProducto.menuSwing();
	    	    	frame.dispose();
	    	    }
	    	});
	          
	    frame.setSize(600, 700);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
}

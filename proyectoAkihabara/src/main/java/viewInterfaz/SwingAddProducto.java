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
 * Clase que usa jframe para añadir un producto
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingAddProducto {
	
	static DatabaseConnection conexion = new DatabaseConnection();
	static ProductoDAO dao = new ProductoDAO(conexion);
	/**
	 * Función que mostrará los campos para añadir un nuevo producto
	 */
	public static void AddProductoSwing() {
	    JFrame frame = new JFrame("Añadir Nuevo Producto");
	    frame.setResizable(false);

	    JLabel titulo = new JLabel("Añadir Nuevo Producto al Inventario");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(127, 24, 322, 30);
	    frame.getContentPane().add(titulo);

	    JLabel nombre = new JLabel("Nombre del Producto:");
	    nombre.setBounds(50, 80, 150, 25);
	    JTextField campoNombre = new JTextField();
	    campoNombre.setBounds(200, 80, 300, 25);
	    frame.getContentPane().add(nombre);
	    frame.getContentPane().add(campoNombre);

	    JLabel categoria = new JLabel("Categoría del Producto:");
	    categoria.setBounds(50, 130, 150, 25);
	    JTextField campoCategoria = new JTextField();
	    campoCategoria.setBounds(200, 130, 300, 25);
	    frame.getContentPane().add(categoria);
	    frame.getContentPane().add(campoCategoria);

	    JLabel precio = new JLabel("Precio del Producto:");
	    precio.setBounds(50, 180, 150, 25);
	    JTextField campoPrecio = new JTextField();
	    campoPrecio.setBounds(200, 180, 300, 25);
	    frame.getContentPane().add(precio);
	    frame.getContentPane().add(campoPrecio);

	    JLabel stock = new JLabel("Stock del Producto:");
	    stock.setBounds(50, 230, 150, 25);
	    JTextField campoStock = new JTextField();
	    campoStock.setBounds(200, 230, 300, 25);
	    frame.getContentPane().add(stock);
	    frame.getContentPane().add(campoStock);

	    JButton guardar = new JButton("Guardar Producto");
	    guardar.setBounds(100, 300, 180, 40);
	    frame.getContentPane().add(guardar);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(300, 300, 180, 40);
	    frame.getContentPane().add(volver);

	    guardar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String nombreadd = campoNombre.getText().trim();
	            String categoriaadd = campoCategoria.getText().trim();
	            String precioadd = campoPrecio.getText().trim();
	            String stockadd = campoStock.getText().trim();
	            if (nombreadd.isEmpty()||categoriaadd.isEmpty()||precioadd.isEmpty()||stockadd.isEmpty()){
	            	JOptionPane.showMessageDialog(null, "Rellene todos los campos por favor.","Error",JOptionPane.ERROR_MESSAGE);
	            } else {
	            	try {
	            		double preciodecimal = Double.parseDouble(precioadd);
		            	int stocknumeric = Integer.parseInt(stockadd);
		            	if (preciodecimal <= 0) {
		            		JOptionPane.showMessageDialog(null, "El precio sebe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
		            	} else if (stocknumeric < 0) {
		            		JOptionPane.showMessageDialog(null, "El stock no puede ser menos que 0.", "Error", JOptionPane.ERROR_MESSAGE);
		            	} else {
		            		ProductoOtaku producto = new ProductoOtaku(nombreadd, categoriaadd, preciodecimal, stocknumeric);
		            		dao.agregarProducto(producto);
		            		System.out.println("Se ha añadido el producto: "+producto);
		            		productoAdded();
		            		frame.dispose();
		            	}
	            	} catch (NumberFormatException e1) {
	            	    JOptionPane.showMessageDialog(null, "El precio y el stock deben ser valores númericos.", "Error", JOptionPane.ERROR_MESSAGE);
	            	}
	            }
	        }
	    });
	   
	    volver.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            SwingMenuProducto.menuSwing();
	            frame.setVisible(false);
	        }
	    });

	    frame.setSize(600, 450);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	/**
	 * Función que se mostrará al añadir el nuevo producto
	 */
	public static void productoAdded() {
		JFrame frame = new JFrame("Producto Añadido");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("El Producto se ha Añadido Correctamente");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(107, 11, 370, 50);
	    frame.getContentPane().add(titulo);
	    
	    JButton volver = new JButton("Volver");
	     volver.setBounds(64, 106, 470, 50);  
	     frame.getContentPane().add(volver);
	     
	     volver.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingMenuProducto.menuSwing();
	    	    	frame.dispose();
	    	    }
	    	});
	          
	    frame.setSize(600, 251);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	
	
}

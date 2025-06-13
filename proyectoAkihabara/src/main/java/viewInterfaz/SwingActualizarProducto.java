package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.ProductoOtaku;
import dao.DatabaseConnection;
import dao.ProductoDAO;
import java.awt.Font;
/**
 * Clase que usa jframe para actualiar un producto
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingActualizarProducto {
	static DatabaseConnection conexion = new DatabaseConnection();
	static ProductoDAO dao = new ProductoDAO(conexion);
	/**
	 * Función que mostrará un campo en el que ingresaremos la id del producto que queremos actualizar
	 */
	public static void actualizarProductoSwing() {
		JFrame frame = new JFrame("Actualizar Producto Akihabara");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("Producto a Actualizar");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(199, 43, 188, 50);
	    frame.getContentPane().add(titulo);
	    
	    JLabel IDProd = new JLabel("ID del Producto:");
	    IDProd.setBounds(79, 148, 150, 25);
	    JTextField IDBuscar = new JTextField();
	    IDBuscar.setBounds(239, 148, 300, 25);
	    frame.getContentPane().add(IDProd);
	    frame.getContentPane().add(IDBuscar);    
	    
	    JButton guardar = new JButton("Buscar Producto");
	    guardar.setBounds(100, 300, 180, 40);
	    frame.getContentPane().add(guardar);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(300, 300, 180, 40);
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
		            		menuActualizar(producto);
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
	          
	    frame.setSize(600, 428);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	/**
	 * Función que mostrará el submenú con las opciones a actualizar del prodcuto
	 * @param producto recibe el producto que modificaremos
	 */
	public static void menuActualizar(ProductoOtaku producto) {
	    JFrame frame = new JFrame("Editar Producto ID: " + producto.getId());
	    frame.setResizable(false);
	    frame.setSize(600, 500);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    frame.setLocationRelativeTo(null);

	    JLabel titulo = new JLabel("Seleccione la opción que desea actualizar");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(99, 39, 370, 30);
	    frame.getContentPane().add(titulo);

	    JButton actnombre = new JButton("Nombre");
	    actnombre.setBounds(50, 98, 220, 50);
	    frame.getContentPane().add(actnombre);

	    JButton actcat = new JButton("Categoria");
	    actcat.setBounds(50, 178, 220, 50);
	    frame.getContentPane().add(actcat);

	    JButton actprecio = new JButton("Precio");
	    actprecio.setBounds(300, 98, 220, 50);
	    frame.getContentPane().add(actprecio);

	    JButton actstock = new JButton("Stock");
	    actstock.setBounds(300, 178, 220, 50);
	    frame.getContentPane().add(actstock);
	    
	    JButton guardar = new JButton("Guardar");
	    guardar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    guardar.setBounds(300, 307, 188, 37);
	    frame.getContentPane().add(guardar);
	    
	    JButton volver = new JButton("Volver");
	    volver.setBounds(82, 307, 188, 37);
	    frame.getContentPane().add(volver);

	    actnombre.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String nuevoNombre = JOptionPane.showInputDialog(frame, "Ingrese el nuevo nombre:", producto.getNombre());
	            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
	                producto.setNombre(nuevoNombre.trim());
	                dao.actualizarProducto(producto);
	                JOptionPane.showMessageDialog(frame, "Nombre actualizado correctamente.");
	            }
	        }
	    });

	    actcat.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String nuevaCategoria = JOptionPane.showInputDialog(frame, "Ingrese la nueva categoría:", producto.getCategoria());
	            if (nuevaCategoria != null && !nuevaCategoria.trim().isEmpty()) {
	                producto.setCategoria(nuevaCategoria.trim());
	                dao.actualizarProducto(producto);
	                JOptionPane.showMessageDialog(frame, "Categoría actualizada correctamente.");
	            }
	        }
	    });

	    actprecio.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String nuevoPrecioStr = JOptionPane.showInputDialog(frame, "Ingrese el nuevo precio:", producto.getPrecio());
	            try {
	                double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
	                if(nuevoPrecio <= 0) {
	                	JOptionPane.showMessageDialog(frame, "El precio debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else {
	                	producto.setPrecio(nuevoPrecio);
		                dao.actualizarProducto(producto);
		                JOptionPane.showMessageDialog(frame, "Precio actualizado correctamente.");
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(frame, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });

	    actstock.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String nuevoStockStr = JOptionPane.showInputDialog(frame, "Ingrese el nuevo stock:", producto.getStock());
	            try {
	                int nuevoStock = Integer.parseInt(nuevoStockStr);
	                if (nuevoStock < 0) {
	                	JOptionPane.showMessageDialog(frame, "El stock no puede ser menor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else {
	                	producto.setStock(nuevoStock);
		                dao.actualizarProducto(producto);
		                JOptionPane.showMessageDialog(frame, "Stock actualizado correctamente.");
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(frame, "Ingrese un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });
	    
	    guardar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dao.actualizarProducto(producto);
	            productoActualizado();
	            frame.setVisible(false);
	        }
	    });
	    
	    volver.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            SwingMenuProducto.menuSwing();
	            frame.setVisible(false);
	        }
	    });

	    frame.setVisible(true);
	}
	/**
	 * Fución que se mostrará a la hora de actualizar el producto
	 */
	public static void productoActualizado() {
		JFrame frame = new JFrame("Producto Actualizado");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("El Producto se ha Actualizado Correctamente");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(88, 30, 400, 50);
	    frame.getContentPane().add(titulo);
	    
	    JButton volver = new JButton("Volver");
	     volver.setBounds(51, 146, 470, 50);  
	     frame.getContentPane().add(volver);
	     
	     volver.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingMenuProducto.menuSwing();
	    	    	frame.dispose();
	    	    }
	    	});
	          
	    frame.setSize(600, 303);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
}

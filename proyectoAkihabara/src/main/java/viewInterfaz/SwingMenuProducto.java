package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
/**
 * Clase que usa jframe para mostrar el menú de productos
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingMenuProducto {	
	/**
	 * Menú con las opciones de gestión de productos
	 */
	public static void menuSwing() {
		JFrame frame = new JFrame("Menú Gestión Inventario Tienda Akihabara");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("Gestion de Inventario Tienda Akihabara");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(106, 43, 354, 50);
	    frame.getContentPane().add(titulo);
	     
	    JButton addprod = new JButton("Añadir Producto");
	    addprod.setBounds(48, 128, 220, 50);
	    frame.getContentPane().add(addprod);
	     
	    addprod.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   SwingAddProducto.AddProductoSwing();
	    		   frame.dispose();
	    	    }
	     });
	     
	     JButton consultarprod = new JButton("Consultar Producto por ID");
	     consultarprod.setBounds(48, 228, 220, 50);
	     frame.getContentPane().add(consultarprod);
	     
	     consultarprod.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingConsultarProducto.consultarIDSwing();
	    	    	frame.dispose();
	    	    }
	     });
	     
	     JButton consultarinventario = new JButton("Consultar Inventario");
	     consultarinventario.setBounds(48, 328, 220, 50);  
	     frame.getContentPane().add(consultarinventario);
	     
	     consultarinventario.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingInventarioProducto.mostrarInventarioSwing();
	    	    	frame.dispose();
	    	    }
	     });
	     
	     JButton actualizarprod = new JButton("Actualizar Producto");
	     actualizarprod.setBounds(298, 128, 220, 50);
	     frame.getContentPane().add(actualizarprod);
	     
	     actualizarprod.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingActualizarProducto.actualizarProductoSwing();
	    	    	frame.dispose();
	    	    }
	     });
	     
	     JButton eliminarprod = new JButton("Eliminar Producto");
	     eliminarprod.setBounds(298, 228, 220, 50);
	     frame.getContentPane().add(eliminarprod);
	     
	     eliminarprod.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingEliminarProducto.eliminarProductoSwing();
	    	    	frame.dispose();
	    	    }
	     });
	     
	     JButton asistenteia = new JButton("Asistente IA");
	     asistenteia.setBounds(298, 328, 220, 50);  
	     frame.getContentPane().add(asistenteia);
	     
	     asistenteia.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingAsistenteProducto.asistenteIASwing();
	    	    	frame.dispose();
	    	    }
	     });
	     
	     JButton salir = new JButton("Salir");
	     salir.setBounds(48, 428, 470, 50);  
	     frame.getContentPane().add(salir);
	     
	     salir.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	        SwingMenuPrincipal.menuSwing();
	    	        frame.dispose();
	    	    }
	     });
	         
	     frame.setSize(600, 544);
	     frame.getContentPane().setLayout(null);
	     frame.setVisible(true);
	}
}

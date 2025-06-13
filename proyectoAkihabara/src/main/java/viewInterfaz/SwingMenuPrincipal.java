package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
/**
 * Clase que usa jframe para mostrar el menú principal
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingMenuPrincipal {	
	/**
	 * Menú principal de la aplicación
	 */
	public static void menuSwing() {
		JFrame frame = new JFrame("Menú Pricnipal Tienda Akihabara");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("Menú Pricnipal Tienda Akihabara");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(140, 67, 291, 50);
	    frame.getContentPane().add(titulo);
	     
	    JButton gestionarcli = new JButton("Gestionar Productos");
	    gestionarcli.setBounds(48, 128, 220, 105);
	    frame.getContentPane().add(gestionarcli);
	     
	    gestionarcli.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   SwingMenuProducto.menuSwing();
	    		   frame.dispose();
	    	    }
	     });
	     
	     JButton gestionarprod = new JButton("Gestionar Clientes");
	     gestionarprod.setBounds(278, 128, 220, 105);
	     frame.getContentPane().add(gestionarprod);
	     
	     gestionarprod.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingMenuClientes.menuSwing();
	    	    	frame.dispose();
	    	    }
	     });
	     
	     JButton salir = new JButton("Salir");
	     salir.setBounds(48, 296, 450, 50);  
	     frame.getContentPane().add(salir);
	     
	     salir.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	        frame.setVisible(false);
	    	    }
	     });
	         
	     frame.setSize(600, 473);
	     frame.getContentPane().setLayout(null);
	     frame.setVisible(true);
	}
}
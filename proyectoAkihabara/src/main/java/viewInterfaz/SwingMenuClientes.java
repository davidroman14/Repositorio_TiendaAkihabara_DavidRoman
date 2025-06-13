package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
/**
 * Clase que usa jframe para mostrar el menú de clientes
 * @author David Román
 * @version 1.0
 */
public class SwingMenuClientes {	
	/**
	 * Menú con las opciones de gestión de clientes
	 */
	public static void menuSwing() {
		JFrame frame = new JFrame("Menú Gestión Clientes Tienda Akihabara");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("Gestion de Clientes Tienda Akihabara");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(113, 11, 343, 50);
	    frame.getContentPane().add(titulo);
	     
	    JButton addcli = new JButton("Añadir Cliente");
	    addcli.setBounds(48, 128, 220, 50);
	    frame.getContentPane().add(addcli);
	     
	    addcli.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   SwingAddCliente.AddClienteSwing();
	    		   frame.dispose();
	    	    }
	     });
	     
	     JButton consultarcli = new JButton("Consultar Cliente por ID");
	     consultarcli.setBounds(48, 228, 220, 50);
	     frame.getContentPane().add(consultarcli);
	     
	     consultarcli.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingConsultarCliente.consultarIdClienteSwing();
	    	    	frame.dispose();
	    	    }
	     });
	     
	     JButton consultarlista = new JButton("Consultar Lista de Clientes");
	     consultarlista.setBounds(48, 328, 220, 50);  
	     frame.getContentPane().add(consultarlista);
	     
	     consultarlista.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingListaClientes.mostrarClientesSwing();
	    	    	frame.dispose();
	    	    }
	     });
	     
	     JButton actualizarcli = new JButton("Actualizar Cliente");
	     actualizarcli.setBounds(298, 128, 220, 50);
	     frame.getContentPane().add(actualizarcli);
	     
	     actualizarcli.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingActualizarCliente.actualizarClienteSwing();
	    	    	frame.dispose();
	    	    }
	     });
	     
	     JButton eliminarcli = new JButton("Eliminar Cliente");
	     eliminarcli.setBounds(298, 228, 220, 50);
	     frame.getContentPane().add(eliminarcli);
	     
	     eliminarcli.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingEliminarCliente.eliminarClienteSwing();
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

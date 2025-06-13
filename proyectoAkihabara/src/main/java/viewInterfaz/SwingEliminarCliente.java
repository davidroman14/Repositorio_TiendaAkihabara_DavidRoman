package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.ClienteDAO;
import dao.DatabaseConnection;
import model.ClienteOtaku;
import java.awt.Font;
/**
 * Clase que usa jframe para eliminar un cliente
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingEliminarCliente {

	static DatabaseConnection conexion = new DatabaseConnection();
	static ClienteDAO daoc = new ClienteDAO(conexion);
	/**
	 * Función en la que mostraremos un campo en el que ingresaremos la id del clitente que queremos eliminar
	 */
	public static void eliminarClienteSwing() {
		JFrame frame = new JFrame("Eliminar Cliente Akihabara");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("ID del Cliente a Eliminar");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(167, 19, 233, 50);
	    frame.getContentPane().add(titulo);
	    
	    JLabel IDProd = new JLabel("ID del Cliente:");
	    IDProd.setBounds(48, 112, 150, 25);
	    JTextField IDBuscar = new JTextField();
	    IDBuscar.setBounds(198, 112, 300, 25);
	    frame.getContentPane().add(IDProd);
	    frame.getContentPane().add(IDBuscar);    
	    
	    JButton guardar = new JButton("Buscar Cliente");
	    guardar.setBounds(100, 186, 180, 40);
	    frame.getContentPane().add(guardar);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(300, 186, 180, 40);
	    frame.getContentPane().add(volver);
	    
	    guardar.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	String IDCli = IDBuscar.getText().trim();
    	    	if (IDCli.isEmpty()){
	            	JOptionPane.showMessageDialog(null, "Rellene la ID por favor.","Error",JOptionPane.ERROR_MESSAGE);
	            } else {
	            	try {
	            		int IDCliNumeric = Integer.parseInt(IDCli);
	            		ClienteOtaku cliente = daoc.obtenerClientePorId(IDCliNumeric);
		            	if (cliente.getId() == 0) {
		            		JOptionPane.showMessageDialog(null, "No hay un cliente con esta ID.","Error",JOptionPane.ERROR_MESSAGE);
		            	} else {
		            		daoc.eliminarCliente(IDCliNumeric);
		            		clienteEliminado();
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
    	    	SwingMenuClientes.menuSwing();
    	    	frame.dispose();
    	    }
    	});
	          
	    frame.setSize(600, 314);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	/**
	 * Función que nos indicará que el cliente ha sido eliminado
	 */
	public static void clienteEliminado() {
		JFrame frame = new JFrame("Cliente Eliminado");

	    JLabel titulo = new JLabel("El Cliente se ha Eliminado Correctamente");
	    titulo.setBounds(175, 50, 1000, 50);
	    frame.getContentPane().add(titulo);
	    
	    JButton volver = new JButton("Volver");
	     volver.setBounds(50, 500, 470, 50);  
	     frame.getContentPane().add(volver);
	     
	     volver.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingMenuClientes.menuSwing();
	    	    	frame.dispose();
	    	    }
	    	});
	          
	    frame.setSize(600, 700);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
}

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

import dao.ClienteDAO;
import dao.DatabaseConnection;
import model.ClienteOtaku;
import java.awt.Font;
/**
 * Clase que usa jframe para consultar un cliente por id
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingConsultarCliente {
	
	static DatabaseConnection conexion = new DatabaseConnection();
	static ClienteDAO daoc = new ClienteDAO(conexion);
	
	/**
	 * Función en la que ingresaremos una id y nos mostrará toda la información del cliente
	 */
	public static void consultarIdClienteSwing() {
		JFrame frame = new JFrame("Cliente Akihabara");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("ID del cliente");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(200, 11, 138, 50);
	    frame.getContentPane().add(titulo);
	    
	    JLabel IDProd = new JLabel("ID del cliente:");
	    IDProd.setBounds(50, 80, 150, 25);
	    JTextField IDBuscar = new JTextField();
	    IDBuscar.setBounds(200, 80, 300, 25);
	    frame.getContentPane().add(IDProd);
	    frame.getContentPane().add(IDBuscar);    
	    
	    JButton guardar = new JButton("Buscar cliente");
	    guardar.setBounds(98, 145, 180, 40);
	    frame.getContentPane().add(guardar);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(288, 145, 180, 40);
	    frame.getContentPane().add(volver);
	    
	    guardar.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	String IDCli = IDBuscar.getText().trim();
    	    	if (IDCli.isEmpty()){
	            	JOptionPane.showMessageDialog(null, "Rellene la ID por favor.","Error",JOptionPane.ERROR_MESSAGE);
	            } else {
	            	try {
	            		int IDClienteNumeric = Integer.parseInt(IDCli);
	            		ClienteOtaku cliente = daoc.obtenerClientePorId(IDClienteNumeric);
		            	if (cliente.getId() == 0) {
		            		JOptionPane.showMessageDialog(null, "No hay un cliente con esta ID.","Error",JOptionPane.ERROR_MESSAGE);
		            	} else {
		            		String[][] datos = new String[1][5];
		            		String[] columnas = {"ID", "Nombre", "Email", "Telefono", "Fecha de Registro"};
		            		datos[0][0] = String.valueOf(cliente.getId());
		        	        datos[0][1] = cliente.getNombre();
		        	        datos[0][2] = cliente.getEmail();
		        	        datos[0][3] = cliente.getTelefono();
		        	        datos[0][4] = String.valueOf(cliente.getFecha_registro());
		        	        
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
    	    	SwingMenuClientes.menuSwing();
    	    	frame.dispose();
    	    }
    	});
	          
	    frame.setSize(600, 269);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
}

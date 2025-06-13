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
 * Clase que usa jframe para añadir un cliente
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingAddCliente {
	
	static DatabaseConnection conexion = new DatabaseConnection();
	static ClienteDAO daoc = new ClienteDAO(conexion);
	/**
	 * Función con los campos necesarios para crear el nuevo cliente
	 */
	public static void AddClienteSwing() {
	    JFrame frame = new JFrame("Añadir Nuevo Cliente");
	    frame.setResizable(false);

	    JLabel titulo = new JLabel("Añadir Nuevo Cliente");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(127, 24, 322, 30);
	    frame.getContentPane().add(titulo);

	    JLabel nombre = new JLabel("Nombre del cliente:");
	    nombre.setBounds(50, 80, 150, 25);
	    JTextField campoNombre = new JTextField();
	    campoNombre.setBounds(200, 80, 300, 25);
	    frame.getContentPane().add(nombre);
	    frame.getContentPane().add(campoNombre);

	    JLabel email = new JLabel("Email del cliente:");
	    email.setBounds(50, 130, 150, 25);
	    JTextField campoEmail = new JTextField();
	    campoEmail.setBounds(200, 130, 300, 25);
	    frame.getContentPane().add(email);
	    frame.getContentPane().add(campoEmail);

	    JLabel telefono = new JLabel("Teléfono del cliente:");
	    telefono.setBounds(50, 180, 150, 25);
	    JTextField campoTelefono = new JTextField();
	    campoTelefono.setBounds(200, 180, 300, 25);
	    frame.getContentPane().add(telefono);
	    frame.getContentPane().add(campoTelefono);

	    JButton guardar = new JButton("Guardar Cliente");
	    guardar.setBounds(100, 300, 180, 40);
	    frame.getContentPane().add(guardar);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(300, 300, 180, 40);
	    frame.getContentPane().add(volver);

	    guardar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String nombreadd = campoNombre.getText().trim();
	            String emailadd = campoEmail.getText().trim();
	            String telefonoadd = campoTelefono.getText().trim();
	            if (nombreadd.isEmpty()||emailadd.isEmpty()||telefonoadd.isEmpty()){
	            	JOptionPane.showMessageDialog(null, "Rellene todos los campos por favor.","Error",JOptionPane.ERROR_MESSAGE);
	            } else {
	            	ClienteOtaku cliente = new ClienteOtaku(nombreadd, emailadd, telefonoadd);
            		daoc.agregarCliente(cliente);
            		clienteAdded();
            		frame.dispose();
	            }
	        }
	    });
	   
	    volver.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	SwingMenuClientes.menuSwing();
    	    	frame.dispose();
    	    }
    	});

	    frame.setSize(600, 450);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	/**
	 * Función que se mostrará si el cliente se ha añadido correctamente	
	 */
	public static void clienteAdded() {
		JFrame frame = new JFrame("Cliente Añadido");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("El Cliente se ha Añadido Correctamente");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(107, 11, 370, 50);
	    frame.getContentPane().add(titulo);
	    
	    JButton volver = new JButton("Volver");
	     volver.setBounds(64, 106, 470, 50);  
	     frame.getContentPane().add(volver);
	     
	     volver.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingMenuClientes.menuSwing();
	    	    	frame.dispose();
	    	    }
	    	});
	          
	    frame.setSize(600, 251);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	
	
}

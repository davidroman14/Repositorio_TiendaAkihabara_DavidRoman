package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.ClienteOtaku;
import dao.ClienteDAO;
import dao.DatabaseConnection;
import java.awt.Font;
/**
 * Clase que usa jframe para actualiar un cliente
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingActualizarCliente {
	static DatabaseConnection conexion = new DatabaseConnection();
	static ClienteDAO daoc = new ClienteDAO(conexion);
	/**
	 * Función que mostrará un campo en el que ingresaremos la id del cliente que queremos actualizar
	 */
	public static void actualizarClienteSwing() {
		JFrame frame = new JFrame("Actualizar Cliente Akihabara");
		frame.setResizable(false);
		
	    JLabel titulo = new JLabel("Cliente a Actualizar");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(199, 43, 188, 50);
	    frame.getContentPane().add(titulo);
	    // Campo en el que ingresaremos la id
	    JLabel IDCli = new JLabel("ID del Cliente:");
	    IDCli.setBounds(79, 148, 150, 25);
	    JTextField IDBuscar = new JTextField();
	    IDBuscar.setBounds(239, 148, 300, 25);
	    frame.getContentPane().add(IDCli);
	    frame.getContentPane().add(IDBuscar);    
	    // Botón para buscar el cliente
	    JButton guardar = new JButton("Buscar Cliente");
	    guardar.setBounds(100, 300, 180, 40);
	    frame.getContentPane().add(guardar);
	    // Botón para volver al menú
	    JButton volver = new JButton("Volver");
	    volver.setBounds(300, 300, 180, 40);
	    frame.getContentPane().add(volver);
	    // Si hacemos clic en el botón de buscar cliente nos lo buscará usando el dao,
	    // Si no lo puede encontrar mostrará porque y si lo encuentra nos mandará al submenú para actualizar
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
		            		menuActualizar(cliente);
		            		frame.dispose();
		            	}
	            	} catch (NumberFormatException e1) {
	            	    JOptionPane.showMessageDialog(null, "La ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
	            	}            	
	            }
    	    }
    	});
	     // Acción del botón para volver al menú
	     volver.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingMenuClientes.menuSwing();
	    	    	frame.dispose();
	    	    }
	    	});
	          
	    frame.setSize(600, 428);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	/**
	 * Función que mostrará el submenú con las opciones a actualizar del cliente
	 * @param cliente recibe el cliente que modificaremos
	 */
	public static void menuActualizar(ClienteOtaku cliente) {
	    JFrame frame = new JFrame("Editar Cliente ID: " + cliente.getId());
	    frame.setResizable(false);
	    frame.setSize(600, 500);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    frame.setLocationRelativeTo(null);

	    JLabel titulo = new JLabel("Seleccione la opción que desea actualizar");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(99, 39, 370, 30);
	    frame.getContentPane().add(titulo);
	    // Botones con las opciones
	    JButton actnombre = new JButton("Nombre");
	    actnombre.setBounds(50, 98, 220, 50);
	    frame.getContentPane().add(actnombre);

	    JButton actemail = new JButton("Email");
	    actemail.setBounds(50, 178, 220, 50);
	    frame.getContentPane().add(actemail);

	    JButton acttelf = new JButton("Teléfono");
	    acttelf.setBounds(300, 98, 220, 50);
	    frame.getContentPane().add(acttelf);
	    
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
	    // Acciones de los botones que mostrarán un campo en el que editaremos la opción seleccionada
	    actnombre.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String nuevoNombre = JOptionPane.showInputDialog(frame, "Ingrese el nuevo nombre:", cliente.getNombre());
	            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
	                cliente.setNombre(nuevoNombre.trim());
	                daoc.actualizarCliente(cliente);
	                JOptionPane.showMessageDialog(frame, "Nombre actualizado correctamente.");
	            }
	        }
	    });

	    actemail.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String nuevoEmail = JOptionPane.showInputDialog(frame, "Ingrese el nuevo Email:", cliente.getEmail());
	            if (nuevoEmail != null && !nuevoEmail.trim().isEmpty()) {
	            	cliente.setEmail(nuevoEmail.trim());
	                daoc.actualizarCliente(cliente);
	                JOptionPane.showMessageDialog(frame, "Email actualizado correctamente.");
	            }
	        }
	    });

	    acttelf.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	            String nuevoTlf = JOptionPane.showInputDialog(frame, "Ingrese el nuevo teléfono:", cliente.getTelefono());
	            if (nuevoTlf != null && !nuevoTlf.trim().isEmpty()) {
	            	cliente.setTelefono(nuevoTlf.trim());;
	                daoc.actualizarCliente(cliente);
	                JOptionPane.showMessageDialog(frame, "teléfono actualizado correctamente.");
	            }
	        }
	    });
	    // Acción para guardar el cliente actualizado
	    guardar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            daoc.actualizarCliente(cliente);
	            clienteActualizado();
	            frame.setVisible(false);
	        }
	    });
	    // Acción para volver al menú
	    volver.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	SwingMenuClientes.menuSwing();
    	    	frame.dispose();
    	    }
    	});

	    frame.setVisible(true);
	}
	/**
	 * Fución que se mostrará a la hora de actualizar el cliente
	 */
	public static void clienteActualizado() {
		JFrame frame = new JFrame("Cliente Actualizado");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("El Cliente se ha Actualizado Correctamente");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(88, 30, 400, 50);
	    frame.getContentPane().add(titulo);
	    
	    JButton volver = new JButton("Volver");
	     volver.setBounds(51, 146, 470, 50);  
	     frame.getContentPane().add(volver);
	     
	     volver.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingMenuClientes.menuSwing();
	    	    	frame.dispose();
	    	    }
	    	});
	          
	    frame.setSize(600, 303);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
}

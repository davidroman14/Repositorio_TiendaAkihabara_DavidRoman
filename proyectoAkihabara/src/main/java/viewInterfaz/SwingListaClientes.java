package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.ClienteDAO;
import dao.DatabaseConnection;
import model.ClienteOtaku;
import java.awt.Font;
/**
 * Clase que usa jframe para mostrar la lista de clientes
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingListaClientes {
	
	static DatabaseConnection conexion = new DatabaseConnection();
	static ClienteDAO daoc = new ClienteDAO(conexion);
	/**
	 * Función que mostrará la lista con todos los clientes registrados
	 */
	public static void mostrarClientesSwing() {
	    JFrame frame = new JFrame("Lista Clientes Akihabara");
	    frame.setResizable(false);

	    JLabel titulo = new JLabel("Lista Clientes Akihabara");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(166, 29, 253, 30);
	    frame.getContentPane().add(titulo);

	    List<ClienteOtaku> clientes = daoc.obtenerTodosLosClientes();
	    
	    String[] columnas = {"ID", "Nombre", "Email", "Telefono", "Fecha de Registro"};

	    String[][] datos = new String[clientes.size()][5];
	    for (int i = 0; i < clientes.size(); i++) {
	        ClienteOtaku cliente = clientes.get(i);
	        datos[i][0] = String.valueOf(cliente.getId());
	        datos[i][1] = cliente.getNombre();
	        datos[i][2] = cliente.getEmail();
	        datos[i][3] = cliente.getTelefono();
	        datos[i][4] = String.valueOf(cliente.getFecha_registro());
	    }

	    JTable tabla = new JTable(datos, columnas);
	    JScrollPane scrollPane = new JScrollPane(tabla);
	    scrollPane.setBounds(30, 70, 520, 300);
	    frame.getContentPane().add(scrollPane);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(200, 400, 200, 40);
	    frame.getContentPane().add(volver);

	    volver.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	SwingMenuClientes.menuSwing();
    	    	frame.dispose();
    	    }
    	});

	    frame.setSize(600, 500);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	
}

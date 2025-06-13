package dao;

import java.util.List;

import model.ClienteOtaku;
/**
 * Interfaz de las funciones del dao de clientes.
 * 
 * @author David Román
 * @version 1.0
 */
public interface ClienteDAOInterfaz {
	public boolean agregarCliente(ClienteOtaku cliente);
	
	public ClienteOtaku obtenerClientePorId(int id);
	
	public List<ClienteOtaku> obtenerTodosLosClientes();
	
	public boolean eliminarCliente (int id);
	
	public boolean actualizarCliente(ClienteOtaku cliente);
	
	public ClienteOtaku buscarPorEmail(String email);
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.ClienteOtaku;
import model.ProductoOtaku;
/**
 * Clase con la que interactuaremos con la tabla de clientes en la base de datos sql.
 * 
 * @author David Román
 * @version 1.0
 */
public class ClienteDAO implements ClienteDAOInterfaz{

	private Connection conn;
	
	public ClienteDAO(DatabaseConnection conexion) {
		this.conn = conexion.getConexion();
	}
	
	@Override
	public boolean agregarCliente(ClienteOtaku cliente) {
		try {
			String query1 = "insert into clientes (nombre, email, telefono) values (?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(query1);
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getTelefono());
			int affect = stmt.executeUpdate();
			if (affect != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLIntegrityConstraintViolationException e){
			System.out.println("Ya ha un cliente registrado con esté email.");
		} catch (SQLException e2){
			System.out.println(e2);
		}
		return false;
	}
	
	@Override
	public ClienteOtaku obtenerClientePorId(int id) {
		ClienteOtaku cliente = new ClienteOtaku();
		try {
			String query1 = "Select * from clientes where id = ?;";
			PreparedStatement stmt = conn.prepareStatement(query1);
			stmt.setInt(1, id);
			ResultSet rst = stmt.executeQuery();
			if(rst.next()){
				cliente.setId(rst.getInt(1));
				cliente.setNombre(rst.getString(2));
				cliente.setEmail(rst.getString(3));
				cliente.setTelefono(rst.getString(4));
				cliente.setFecha_registro(rst.getString(5));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e){
			System.out.println(e);
		}
		return cliente;
	}
	
	@Override
	public List<ClienteOtaku> obtenerTodosLosClientes(){
		List<ClienteOtaku> lista = new ArrayList<ClienteOtaku>();
		try {
			String query1 = "Select * from clientes;";
			PreparedStatement stmt = conn.prepareStatement(query1);
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				ClienteOtaku cliente = new ClienteOtaku();
				cliente.setId(rst.getInt(1));
				cliente.setNombre(rst.getString(2));
				cliente.setEmail(rst.getString(3));
				cliente.setTelefono(rst.getString(4));
				cliente.setFecha_registro(rst.getString(5));
				lista.add(cliente);
			}
			rst.close();
			stmt.close();
		} catch (SQLException e){
			System.out.println(e);
		}
		return lista;
	}
	
	@Override
	public boolean eliminarCliente (int id) {
		try {
			String query1 = "delete from clientes where id = ?;";
			PreparedStatement stmt = conn.prepareStatement(query1);
			stmt.setInt(1, id);
			int affect = stmt.executeUpdate();
			stmt.close();
			if (affect > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e){
			System.out.println(e);
		}
		return false;
	}
	
	@Override
	public boolean actualizarCliente(ClienteOtaku cliente) {
		try {
			String query1 = "UPDATE clientes SET nombre = ?, email = ?, telefono = ? WHERE id = ?;";
			PreparedStatement stmt = conn.prepareStatement(query1);
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getTelefono());
			stmt.setInt(4, cliente.getId());
			int affect = stmt.executeUpdate();
			stmt.close();
			if (affect > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e){
			System.out.println(e);
		}
		return false;
	}
	
	@Override
	public ClienteOtaku buscarPorEmail(String email){
		ClienteOtaku cliente = new ClienteOtaku();
		try {
			String query1 = "Select * from clientes where email = ?;";
			PreparedStatement stmt = conn.prepareStatement(query1);
			stmt.setString(1, email);
			ResultSet rst = stmt.executeQuery();
			if(rst.next()){
				cliente.setId(rst.getInt(1));
				cliente.setNombre(rst.getString(2));
				cliente.setEmail(rst.getString(3));
				cliente.setTelefono(rst.getString(4));
				cliente.setFecha_registro(rst.getString(5));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e){
			System.out.println(e);
		}
		return cliente;
	}
}

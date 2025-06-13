package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductoOtaku;

/**
 * Clase para interactuar con la tabla de productos.
 * Implementa una interfaz que define las funciones que usaremos.
 * 
 * @author David Román
 * @version 1.0
 */

public class ProductoDAO implements ProductoDAOInterfaz{
	
	private Connection conn;
	
	public ProductoDAO(DatabaseConnection conexion) {
		this.conn = conexion.getConexion();
	}
	
	/**
	 * Inserta un nuevo producto en la base de datos.
	 *
	 * @param producto El producto que se desea agregar.
	 */
	@Override
	public void agregarProducto(ProductoOtaku producto) {
		try {
			String query1 = "insert into productos (nombre, categoria, precio, stock) values (?,?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(query1);
			stmt.setString(1, producto.getNombre());
			stmt.setString(2, producto.getCategoria());
			stmt.setDouble(3, producto.getPrecio());
			stmt.setInt(4,  producto.getStock());
			int affect = stmt.executeUpdate();
		} catch (SQLException e){
			System.out.println(e);
		}
	}
	
	/**
	 * Recupera un producto de la base de datos a partir de su ID.
	 *
	 * @param id El ID del producto a buscar.
	 * @return Un objeto ProductoOtaku con los datos encontrados, o con ID 0 si no se encuentra.
	 */
	@Override
	public ProductoOtaku obtenerProductoId(int id) {
		ProductoOtaku producto = new ProductoOtaku();
		try {
			String query1 = "Select * from productos where id = ?;";
			PreparedStatement stmt = conn.prepareStatement(query1);
			stmt.setInt(1, id);
			ResultSet rst = stmt.executeQuery();
			if(rst.next()){
				producto.setId(rst.getInt(1));
				producto.setNombre(rst.getString(2));
				producto.setCategoria(rst.getString(3));
				producto.setPrecio(rst.getDouble(4));
				producto.setStock(rst.getInt(5));	
			}
			rst.close();
			stmt.close();
		} catch (SQLException e){
			System.out.println(e);
		}
		return producto;
	}
	
	/**
	 * Obtiene todos los productos almacenados en la base de datos.
	 *
	 * @return Una lista con todos los productos registrados.
	 */
	@Override
	public List<ProductoOtaku> obtenerTodosLosProductos(){
		List<ProductoOtaku> lista = new ArrayList<ProductoOtaku>();
		try {
			String query1 = "Select * from productos;";
			PreparedStatement stmt = conn.prepareStatement(query1);
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				ProductoOtaku producto = new ProductoOtaku();
				producto.setId(rst.getInt(1));
				producto.setNombre(rst.getString(2));
				producto.setCategoria(rst.getString(3));
				producto.setPrecio(rst.getDouble(4));
				producto.setStock(rst.getInt(5));
				lista.add(producto);
			}
			rst.close();
			stmt.close();
		} catch (SQLException e){
			System.out.println(e);
		}
		return lista;
	}
	
	/**
	 * Elimina un producto de la base de datos según su ID.
	 *
	 * @param id El ID del producto que se desea eliminar.
	 * @return true si se eliminó correctamente, false si no se encontró o no se pudo eliminar.
	 */
	@Override
	public boolean eliminarProducto (int id) {
		try {
			String query1 = "delete from productos where id = ?;";
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

	/**
	 * Actualiza los datos de un producto existente en la base de datos.
	 *
	 * @param producto El producto con los nuevos datos a actualizar.
	 * @return true si la actualización fue exitosa, false en caso contrario.
	 */
	@Override
	public boolean actualizarProducto(ProductoOtaku producto) {
		try {
			String query1 = "UPDATE productos SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id = ?;";
			PreparedStatement stmt = conn.prepareStatement(query1);
			stmt.setString(1, producto.getNombre());
			stmt.setString(2, producto.getCategoria());
			stmt.setDouble(3, producto.getPrecio());
			stmt.setInt(4, producto.getStock());
			stmt.setInt(5, producto.getId());
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
	
	/**
	 * Busca y devuelve una lista de productos que coincidan exactamente con el nombre proporcionado.
	 *
	 * @param nombre El nombre del producto a buscar.
	 * @return Una lista de productos que coincidan con el nombre especificado.
	 */
	@Override
	public List<ProductoOtaku> buscarProductosPorNombre(String nombre){
		List<ProductoOtaku> lista = new ArrayList<ProductoOtaku>();
		try {
			String query1 = "Select * from productos where nombre = ?;";
			PreparedStatement stmt = conn.prepareStatement(query1);
			stmt.setString(1, nombre);
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				ProductoOtaku producto = new ProductoOtaku();
				producto.setId(rst.getInt(1));
				producto.setNombre(rst.getString(2));
				producto.setCategoria(rst.getString(3));
				producto.setPrecio(rst.getDouble(4));
				producto.setStock(rst.getInt(5));
				lista.add(producto);
			}
			rst.close();
			stmt.close();
		} catch (SQLException e){
			System.out.println(e);
		}
		return lista;
	}
}

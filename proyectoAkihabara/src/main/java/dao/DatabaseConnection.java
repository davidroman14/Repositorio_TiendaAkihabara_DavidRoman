package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para hacer conexión con la base de datos akihabara_db.
 * 
 * @author David Román
 * @version 1.0
 */

public class DatabaseConnection {
	private static final String url = "jdbc:mysql://localhost:3306/akihabara_db";
    private static final String usr = "adminAkihabara";
    private static final String pwd = "campusfp";
    private Connection conn;

	/**
	 * DatabaseConnection() abre la conexión con la base de datos
	 */
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Conexión a la base de datos
			conn = DriverManager.getConnection(url,usr,pwd);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		} catch (SQLException ex) {
			System.out.println(ex);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public Connection getConexion() {
		return conn;
	}
	
	/**
	 * cerrarConexion() cierra la conexión con la base de datos
	 */
	public void cerrarConexion () {
		try {
			conn.close();
		} catch (SQLException e){
			System.out.println(e);
		}
	}
}

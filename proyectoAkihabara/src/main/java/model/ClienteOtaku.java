package model;
/**
 * Clase para crear el objeto de cliente.
 *
 */
public class ClienteOtaku {
	private int id;
	private String nombre;
	private String email;
	private String telefono;
	private String fecha_registro;
	
	public ClienteOtaku(String nombre, String email, String telefono) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}
	
	public ClienteOtaku(int id, String nombre, String email, String telefono, String fecha_registro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.fecha_registro = fecha_registro;
	}
	
	public ClienteOtaku() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	@Override
	public String toString() {
		return "ClienteOtaku [id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono
				+ ", fecha_registro=" + fecha_registro + "]";
	}
}

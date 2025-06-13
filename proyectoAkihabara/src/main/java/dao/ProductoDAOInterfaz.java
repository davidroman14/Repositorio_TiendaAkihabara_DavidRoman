package dao;

import java.util.List;

import model.ProductoOtaku;

public interface ProductoDAOInterfaz {
	public void agregarProducto(ProductoOtaku producto);
	
	public ProductoOtaku obtenerProductoId(int id);
	
	public List<ProductoOtaku> obtenerTodosLosProductos();
	
	public boolean eliminarProducto (int id);
	
	public boolean actualizarProducto(ProductoOtaku producto);
	
	public List<ProductoOtaku> buscarProductosPorNombre(String nombre);
}

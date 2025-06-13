package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.ProductoOtaku;

class ProductoDAOTest {

	@Test
    void testProductoOtakuIntStringStringDoubleInt() {
        ProductoOtaku producto = new ProductoOtaku(1, "Figura Naruto", "Figuras", 29.99, 10);
        assertEquals(1, producto.getId());
        assertEquals("Figura Naruto", producto.getNombre());
        assertEquals("Figuras", producto.getCategoria());
        assertEquals(29.99, producto.getPrecio());
        assertEquals(10, producto.getStock());
    }

    @Test
    void testProductoOtakuStringStringDoubleInt() {
        ProductoOtaku producto = new ProductoOtaku("Figura Luffy", "Figuras", 39.99, 5);
        assertEquals("Figura Luffy", producto.getNombre());
        assertEquals("Figuras", producto.getCategoria());
        assertEquals(39.99, producto.getPrecio());
        assertEquals(5, producto.getStock());
        // No se valida el id porque no se inicializa en este constructor
    }

    @Test
    void testProductoOtaku() {
        ProductoOtaku producto = new ProductoOtaku();
        assertNotNull(producto);
        // Por defecto, todos los atributos estarán en su valor predeterminado (0 o null)
        assertEquals(0, producto.getId());
        assertNull(producto.getNombre());
        assertNull(producto.getCategoria());
        assertEquals(0.0, producto.getPrecio());
        assertEquals(0, producto.getStock());
    }

    @Test
    void testGetId() {
        ProductoOtaku producto = new ProductoOtaku();
        producto.setId(99);
        assertEquals(99, producto.getId());
    }

    @Test
    void testSetId() {
        ProductoOtaku producto = new ProductoOtaku();
        producto.setId(123);
        assertEquals(123, producto.getId());
    }

    @Test
    void testGetNombre() {
        ProductoOtaku producto = new ProductoOtaku();
        producto.setNombre("Camisa One Piece");
        assertEquals("Camisa One Piece", producto.getNombre());
    }

    @Test
    void testSetNombre() {
        ProductoOtaku producto = new ProductoOtaku();
        producto.setNombre("Taza Goku");
        assertEquals("Taza Goku", producto.getNombre());
    }

    @Test
    void testGetCategoria() {
        ProductoOtaku producto = new ProductoOtaku();
        producto.setCategoria("Accesorios");
        assertEquals("Accesorios", producto.getCategoria());
    }

    @Test
    void testSetCategoria() {
        ProductoOtaku producto = new ProductoOtaku();
        producto.setCategoria("Posters");
        assertEquals("Posters", producto.getCategoria());
    }

    @Test
    void testGetPrecio() {
        ProductoOtaku producto = new ProductoOtaku();
        producto.setPrecio(12.50);
        assertEquals(12.50, producto.getPrecio());
    }

    @Test
    void testSetPrecio() {
        ProductoOtaku producto = new ProductoOtaku();
        producto.setPrecio(99.99);
        assertEquals(99.99, producto.getPrecio());
    }

    @Test
    void testGetStock() {
        ProductoOtaku producto = new ProductoOtaku();
        producto.setStock(15);
        assertEquals(15, producto.getStock());
    }

    @Test
    void testSetStock() {
        ProductoOtaku producto = new ProductoOtaku();
        producto.setStock(25);
        assertEquals(25, producto.getStock());
    }

    @Test
    void testToString() {
        ProductoOtaku producto = new ProductoOtaku(1, "Póster Bleach", "Posters", 14.99, 7);
        String esperado = "ProductoOtaku [id = 1, nombre=Póster Bleach, categoria=Posters, precio=14.99, stock=7]";
        assertEquals(esperado, producto.toString());
    }
}

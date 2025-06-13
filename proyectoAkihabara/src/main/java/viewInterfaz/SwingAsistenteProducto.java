package viewInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import service.LlmService;
import dao.DatabaseConnection;
import dao.ProductoDAO;
import model.ProductoOtaku;
import java.awt.Font;
/**
 * Clase que usa jframe para usar el asistente IA
 * 
 * @author David Román
 * @version 1.0
 */
public class SwingAsistenteProducto {
	
	static DatabaseConnection conexion = new DatabaseConnection();
	static ProductoDAO dao = new ProductoDAO(conexion);
	/**
	 * Función que mostrará el submenú con las opciones del asistente IA
	 */
	public static void asistenteIASwing() {
		JFrame frame = new JFrame("Asistente IA Akihabara");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("Asistente IA");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(227, 58, 107, 50);
	    frame.getContentPane().add(titulo);
	    
	    JButton addprod = new JButton("Generar Descripción");
	    addprod.setBounds(29, 140, 220, 111);
	    frame.getContentPane().add(addprod);
	     
	    addprod.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   descripcion();
	    		   frame.dispose();
	    	    }
	     });
	     
	     JButton consultarprod = new JButton("Sugerir Categoria");
	     consultarprod.setBounds(300, 140, 220, 111);
	     frame.getContentPane().add(consultarprod);
	     
	     consultarprod.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	categoria();
	    	    	frame.dispose();
	    	    }
	     });
	    
	    JButton volver = new JButton("Volver");
	     volver.setBounds(29, 340, 491, 50);  
	     frame.getContentPane().add(volver);
	     
	     volver.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingMenuProducto.menuSwing();
	    	    	frame.dispose();
	    	    }
	    	});
	          
	    frame.setSize(600, 482);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	/**
	 * Función para indicar la id del producto al que le queremos generar una descripción
	 */
	public static void descripcion() {
		JFrame frame = new JFrame("Asistente IA Akihabara");
		frame.setResizable(false);
	    
	    JLabel IDProd = new JLabel("ID del Producto:");
	    IDProd.setBounds(60, 171, 150, 25);
	    JTextField IDBuscar = new JTextField();
	    IDBuscar.setBounds(210, 171, 300, 25);
	    frame.getContentPane().add(IDProd);
	    frame.getContentPane().add(IDBuscar);    
	    
	    JButton guardar = new JButton("Generar Descripción");
	    guardar.setBounds(100, 300, 180, 40);
	    frame.getContentPane().add(guardar);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(300, 300, 180, 40);
	    frame.getContentPane().add(volver);
	    
	    guardar.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	String IDProd = IDBuscar.getText().trim();
    	    	if (IDProd.isEmpty()){
	            	JOptionPane.showMessageDialog(null, "Rellene la ID por favor.","Error",JOptionPane.ERROR_MESSAGE);
	            } else {
	            	try {
	            		int IDProdNumeric = Integer.parseInt(IDProd);
	            		ProductoOtaku producto = dao.obtenerProductoId(IDProdNumeric);
		            	if (producto.getId() == 0) {
		            		JOptionPane.showMessageDialog(null, "No hay un producto con esta ID.","Error",JOptionPane.ERROR_MESSAGE);
		            	} else {
		            		mostrarDescripcion(producto);
		            		frame.dispose();
		            	}
	            	} catch (NumberFormatException e1) {
	            	    JOptionPane.showMessageDialog(null, "La ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
	            	}            	
	            }
    	    }
    	});
	     
	    volver.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	SwingMenuProducto.menuSwing();
    	        frame.dispose();
    	    }
    	});
	          
	    frame.setSize(600, 462);
	    frame.getContentPane().setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("Generar Descripción de un Producto");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    lblNewLabel.setBounds(125, 57, 319, 49);
	    frame.getContentPane().add(lblNewLabel);
	    frame.setVisible(true);
	}
	/**
	 * Función que mostrará la descripción del producto
	 * @param producto Producto del que generaremos la descripción
	 */
	public static void mostrarDescripcion(ProductoOtaku producto) {
		JFrame frame = new JFrame("Asistente IA Akihabara");
		frame.setResizable(false);

	    JLabel titulo = new JLabel("Descripción para "+producto.getNombre());
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(33, 46, 541, 30);
	    frame.getContentPane().add(titulo);

	    String prompt = LlmService.enviarPrompt(
				"Genera una descripción de marketing breve y atractiva para el producto otaku:"+
				producto.getNombre() + ", en español porfavor no me des varias opciones, que sea solo una descripción."
				);
	    
	    JTextArea desc = new JTextArea(prompt);
	    desc.setEditable(false);
	    desc.setBounds(33, 112, 526, 154);
	    desc.setLineWrap(true);
	    desc.setWrapStyleWord(true);
	    frame.getContentPane().add(desc);
	    
	    JButton volver = new JButton("Volver");
	    volver.setBounds(72, 333, 470, 50);  
	    frame.getContentPane().add(volver);

	    volver.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            SwingMenuProducto.menuSwing();
	            frame.dispose();
	        }
	    });

	    frame.setSize(600, 500);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	/**
	 * Función en la que ingresaremos el nombre de un nuevo porducto al que le queremos dar una categoría
	 */
	public static void categoria() {
		JFrame frame = new JFrame("Asistente IA Akihabara");

	    JLabel titulo = new JLabel("Sugerir Categoría");
	    titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    titulo.setBounds(189, 11, 163, 50);
	    frame.getContentPane().add(titulo);
	    
	    JLabel NombreProd = new JLabel("Nombre del Producto:");
	    NombreProd.setBounds(50, 80, 150, 25);
	    JTextField NombreBuscar = new JTextField();
	    NombreBuscar.setBounds(199, 80, 300, 25);
	    frame.getContentPane().add(NombreProd);
	    frame.getContentPane().add(NombreBuscar);
	    
	    JButton guardar = new JButton("Sugerir Categoria");
	    guardar.setBounds(102, 161, 180, 40);
	    frame.getContentPane().add(guardar);

	    JButton volver = new JButton("Volver");
	    volver.setBounds(292, 161, 180, 40);
	    frame.getContentPane().add(volver);
	    
	    guardar.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	String nombre = NombreBuscar.getText().trim();
    	    	if (nombre.isEmpty()){
	            	JOptionPane.showMessageDialog(null, "Rellene el nombre por favor.","Error",JOptionPane.ERROR_MESSAGE);
	            } else {
	            	mostrarCategoria(nombre);
            		frame.dispose();          	
	            }
    	    }
    	});
	     
	     volver.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	SwingMenuProducto.menuSwing();
	    	    	frame.dispose();
	    	    }
	    	});
	          
	    frame.setSize(600, 322);
	    frame.getContentPane().setLayout(null);
	    frame.setVisible(true);
	}
	
	/**
	 * Función que mostrará la categoría sugerida por la IA
	 * @param nombre Nombre del producto al que le queremos asignar una categoría
	 */
	public static void mostrarCategoria(String nombre) {
		JFrame frame = new JFrame("Asistente IA Akihabara");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 413);
		frame.getContentPane().setLayout(null);

		JLabel titulo = new JLabel("Sugerencia de categoría para " + nombre);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setBounds(33, 46, 526, 30);
		frame.getContentPane().add(titulo);

		String respuestacategoria = LlmService.enviarPrompt("Para un producto otaku llamado " +
			nombre +
			", sugiere una categoría adecuada de esta lista: Figura, Manga, Póster, Llavero, Ropa, Videojuego, Otro. Que la respuesta sea solo la categoria" +
			" no des ninguna descripción ni nada, añade algo delante de la categoria del estilo: te sugiero la categoria de [categoria que cuadre mejor]");

		JTextArea desc = new JTextArea(respuestacategoria);
		desc.setEditable(false);
		desc.setBounds(33, 112, 526, 154);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		frame.getContentPane().add(desc);

		JButton volver = new JButton("Volver");
		volver.setBounds(66, 300, 470, 50);
		frame.getContentPane().add(volver);

		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingMenuProducto.menuSwing();
				frame.dispose();
			}
		});

		frame.setVisible(true);
	}
}

package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * Clase para leer la api key.
 * 
 * @author David Román
 * @version 1.0
 */
public class ConfigLoader {
	/**
	 * Función para leer la api key.
	 * @return api key del archivo config.properties
	 */
    public static String leerConfig() {
        File archivo = new File("config.properties");
        // Se el archivo no existe mostrará un error
        if (!archivo.exists()) {
            System.err.println("No se ha encontrado el archivo de configuración");
            return null;
        }
        // Si está vacio mostrará un error
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            if (linea == null || linea.trim().isEmpty()) {
                System.err.println("No hay clave API en el archivo de configuración.");
                return null;
            }
            return linea.trim();
        // Si hay algún problema al leer la api key mostrará un error
        } catch (IOException e) {
            System.err.println("Error al leer la API key: " + e.getMessage());
            return null;
        }
    }
}


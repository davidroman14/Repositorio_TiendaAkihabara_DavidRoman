package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import config.ConfigLoader;

/**
 * Clase en la cual interactuaremos con la IA.
 * 
 * @author David Román
 * @version 1.0
 */

public class LlmService {
	// Clave para poder acceder a la API de OpenRouter
    private static String API_KEY = null;
    // URL a la que se enviará la consulta que hagamos
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    // Modelo de la IA que usaremos
    private static final String MODEL = "meta-llama/llama-4-maverick:free";

    /**
     * Envía un prompt de texto a una IA mediante una API y devuelve la respuesta generada.
     *
     * Esta función lee la clave de API desde un archivo local, construye una petición
     * en formato JSON, y la envía a un endpoint definido, por ultimo formateará el contenido
     * del JSON que nos devuelve la IA y lo formateamos para devolverlo con String
     * 
     * @param consulta El texto de la consulta o prompt que se desea enviar al modelo de IA.
     * @return La respuesta generada por la IA si la consulta es válida, si no lo es devolverá un mensaje de error.
     */
    public static String enviarPrompt(String consulta) {
    	// Usamos la función de config loader para leer le api key
    	API_KEY = ConfigLoader.leerConfig();
    	
        try {
        	// Si el mensaje está vacio nos devolverá un mensaje indicandolo para evitar errores
        	if (consulta.trim().equals("")) {
        		return "Ingrese su consulta por favor.";
        	}
        	
        	// Construimos la consulta como un json indicando el modelo y sustituimos las comillas y las barras invertidas 
        	// para evitar errores a la hora de construir el json
            String jsonBody = "{"
                    + "\"model\":\"" + MODEL + "\"," 
                    + "\"messages\":[{\"role\":\"user\", \"content\":\"" + consulta.replace("\"", "\\\"") + "\"}]"
                    + "}";

            // Creamos el cliente que enviará la petición
            HttpClient cliente = HttpClient.newHttpClient();
            // Construimos la petición que enviaremos a la ia
            HttpRequest peticion = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            // El cliente enviará la petición que hemos construido a la ia y nos devilverá el mensaje guardandolo
            // en respuestaIA 
            HttpResponse<String> respuestaIA = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

            // Pasamos la petición a String
            String respuestaString = respuestaIA.body();
            
            JsonObject root = JsonParser.parseString(respuestaString).getAsJsonObject();
            
            JsonArray choices = root.getAsJsonArray("choices");
            JsonObject message = choices.get(0).getAsJsonObject().getAsJsonObject("message");
            String respuestaFinal = message.get("content").getAsString();
            
            return respuestaFinal;

        } catch (Exception e) {
        	// Controlamos los errores y los devolvemos en caso de que haya algún problema a la hora de
        	// comunicarnos con la API
            return e.getMessage();
        }
    }
}

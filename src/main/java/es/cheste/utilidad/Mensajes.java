package es.cheste.utilidad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase para gestionar los mensajes desde un archivo de propiedades.
 * <p>
 * Proporciona métodos para cargar y obtener lso mensajes almacenados en un archivo de propiedades.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
public class Mensajes {

    private static final Properties MENSAJES = new Properties();
    private static final String RUTA_MENSAJES = "src/main/resources/properties/mensajes/mensajes.properties";
    private static final Logger LOGGER = LogManager.getLogger(Mensajes.class);

    /**
     * Bloque estático para cargar los mensajes al cargarse en memoria.
     */
    static {
        try (FileInputStream fis = new FileInputStream(new File(RUTA_MENSAJES))) {
            MENSAJES.load(fis);
        } catch (IOException e) {
            LOGGER.error("Hubo un error al intentar cargar loes mensajes en {} Mensaje de error: {}", RUTA_MENSAJES, e.getMessage());
            throw new ExceptionInInitializerError("No se pudo cargar mensajes.properties: " + e.getMessage());
        }
    }

    /**
     * Obtiene un mensaje por su clave.
     *
     * @param key Clave del mensaje.
     * @return El mensaje correspondiente a la clave proporcionada.
     */
    public static String getMensaje(String key) {
        return MENSAJES.getProperty(key);
    }
}

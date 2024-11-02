package es.cheste.utilidad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase para gestionar las sentencias SQL desde un archivo de propiedades.
 * <p>
 * Proporciona métodos para cargar y obtener sentencias SQL almacenadas en un archivo de propiedades.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
public class SentenciasSQL {

    private static final Properties SENTENCIAS = new Properties();
    private static final String RUTA_SENTENCIAS_SQL = "src/main/resources/properties/sentencias_sql/sentencias_sql.properties";
    private static final Logger LOGGER = LogManager.getLogger(SentenciasSQL.class);

    /**
     * Bloque estático para cargar las sentencias SQL al compilarse la aplicación.
     */
    static {
        try (FileInputStream fis = new FileInputStream(new File(RUTA_SENTENCIAS_SQL))) {
            SENTENCIAS.load(fis);
        } catch (IOException e) {
            LOGGER.error("Hubo un error al intentar cargar las sentencias en {} Mensaje de error: {}", RUTA_SENTENCIAS_SQL, e.getMessage());
            throw new ExceptionInInitializerError("No se pudo cargar sentencias_sql.properties: " + e.getMessage());
        }
    }

    /**
     * Obtiene una sentencia SQL por su clave.
     *
     * @param key Clave de la sentencia SQL.
     * @return La sentencia SQL correspondiente a la clave proporcionada.
     */
    public static String getSentencia(String key) {
        return SENTENCIAS.getProperty(key);
    }
}
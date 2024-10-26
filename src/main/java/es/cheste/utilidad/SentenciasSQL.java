package es.cheste.utilidad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SentenciasSQL {

    private static final Properties SENTENCIAS = new Properties();
    private static final String RUTA_SENTENCIAS_SQL = "src/main/resources/sentencias_sql/sentencias_sql.properties";
    private static final Logger LOGGER = LogManager.getLogger(SentenciasSQL.class);

    static {
        try (FileInputStream fis = new FileInputStream(RUTA_SENTENCIAS_SQL)) {
            SENTENCIAS.load(fis);

        } catch (IOException e) {
            LOGGER.error("Hubo un error al intentar cargar las sentencias en {} Mensaje de error: {}", RUTA_SENTENCIAS_SQL, e.getMessage());
            throw new ExceptionInInitializerError("No se pudo cargar sentencias_sql.properties: " + e.getMessage());
        }
    }

    public static String getSentencia(String key) {
        return SENTENCIAS.getProperty(key);
    }
}

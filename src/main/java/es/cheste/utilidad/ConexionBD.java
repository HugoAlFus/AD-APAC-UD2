package es.cheste.utilidad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase para gestionar la conexión a la base de datos.
 * <p>
 * Proporciona métodos para conectar y desconectar de la base de datos,
 * así como para obtener la conexión actual.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
public class ConexionBD {

    private static final Logger LOGGER = LogManager.getLogger(ConexionBD.class);
    private static final String FILENAME = "src/main/resources/properties/conexion/application.properties";

    private Connection connection = null;

    /**
     * Método privado para establecer la conexión a la base de datos.
     * <p>
     * Carga las propiedades de conexión desde un archivo y establece la conexión
     * utilizando `DriverManager`.
     */
    private void conectar() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(FILENAME)));

            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.pass");

            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            LOGGER.error("Error loading properties file", e);
        } catch (SQLException e) {
            LOGGER.error("Error connecting database", e);
        }
    }

    /**
     * Método para desconectar de la base de datos.
     * <p>
     * Cierra la conexión actual si está abierta.
     */
    public void desconectar() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error disconnecting database", e);
        }
    }

    /**
     * Obtiene la conexión actual a la base de datos.
     * <p>
     * Si no hay una conexión establecida, llama al método `conectar` para establecerla.
     *
     * @return La conexión actual a la base de datos.
     */
    public Connection getConnection() {
        if (connection == null) {
            conectar();
        }
        return connection;
    }
}
package es.cheste.utilidad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase GestorBD que se encarga de la gestión de la base de datos.
 * Incluye métodos para iniciar la base de datos, ejecutar sentencias SQL y comprobar la existencia de tablas.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class GestorBD {

    private static final String RUTA_SQL_CREACION = "src/main/resources/sql/crear_tablas.sql";
    private static final String RUTA_SQL_INSERCION = "src/main/resources/sql/insertar_datos.sql";
    private static final String[] TABLAS = {"PLATO", "CHEF", "CLIENTE", "MESA", "PEDIDO", "CONTENER", "REALIZAR"};
    private static final Logger LOGGER = LogManager.getLogger(GestorBD.class);

    /**
     * Inicia la base de datos. Comprueba si las tablas existen y, si no, ejecuta los scripts de creación e inserción.
     *
     * @return true si la base de datos se inició correctamente, false en caso contrario.
     */
    public static boolean iniciarBaseDatos() {
        ConexionBD conexion = new ConexionBD();
        Connection connection = conexion.getConnection();
        Boolean esValida = Boolean.FALSE;
        if (connection == null) {
            LOGGER.error("No se pudo establecer la conexión con la base de datos.");
            return esValida;
        }

        try {
            if (!comprobarExistenTablas(connection)) {
                connection.setAutoCommit(Boolean.FALSE);
                ejecutarSentenciasSQL(RUTA_SQL_CREACION, connection);
                ejecutarSentenciasSQL(RUTA_SQL_INSERCION, connection);
                connection.commit();
                esValida = Boolean.TRUE;
            } else {
                LOGGER.info("Las tablas ya existen");
                esValida = Boolean.TRUE;
            }
        } catch (SQLException e) {
            LOGGER.error("Error al iniciar la base de datos: {}", e.getMessage());
            try {
                LOGGER.error("Se esta haciendo rollback");
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error("Hubo un error al hacer rollback {}", e.getMessage());
            }
        } finally {
            conexion.desconectar();
        }
        return esValida;
    }

    /**
     * Lee un archivo SQL y lo divide en sentencias SQL utilizando el delimitador especificado.
     *
     * @param ruta Ruta del archivo SQL.
     * @return Un array de sentencias SQL.
     */
    private static String[] leerSQL(String ruta) {
        Path path = Paths.get(ruta);
        String textoSQL;
        String[] listaSQL = null;

        try {

            textoSQL = Files.readString(path);
            listaSQL = textoSQL.split(";");

        } catch (IOException e) {
            LOGGER.error("Hubo un error al leer los datos del archivo '{}' {}", ruta, e.getMessage());
        }
        return listaSQL;
    }

    /**
     * Ejecuta las sentencias SQL de un archivo en la conexión especificada.
     *
     * @param ruta       Ruta del archivo SQL.
     * @param connection Conexión a la base de datos
     * @throws SQLException Si ocurre un error al ejecutar las sentencias SQL.
     */
    private static void ejecutarSentenciasSQL(String ruta, Connection connection) throws SQLException {
        String[] listadoSQL = leerSQL(ruta);
        for (String sql : listadoSQL) {
            if (!sql.trim().isEmpty()) {
                ejecutarSQL(sql.trim(), connection);
            }
        }
    }

    /**
     * Ejecuta una sentencia SQL en la conexión especificada.
     *
     * @param sql        Sentencia SQL a ejecutar.
     * @param connection Conexión a la base de datos.
     * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL.
     */
    private static void ejecutarSQL(String sql, Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            LOGGER.info("Con exito SQL: " + sql);
        } catch (SQLException e) {
            LOGGER.error("Hubo un error al ejecutar la consulta SQL: {} || {}", sql, e.getMessage());
            throw e;
        }
    }

    /**
     * Comprueba si las tablas especificadas existen en la base de datos.
     *
     * @param connection Conexión a la base de datos.
     * @return true si todas las tablas existen, false en caso contrario.
     */
    private static boolean comprobarExistenTablas(Connection connection) {
        boolean hayTabla = Boolean.FALSE;

        try (Statement statement = connection.createStatement()) {
            for (String tabla : TABLAS) {
                String sql = SentenciasSQL.getSentencia("obtener.todos." + tabla.toLowerCase());
                if (sql != null) {
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.next()) {
                        hayTabla = Boolean.TRUE;
                    }
                } else {
                    LOGGER.error("La sentencia SQL para la tabla '{}' es null", tabla);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error al verificar la existencia de las tablas: {}", e.getMessage());
            return Boolean.FALSE;
        }
        return hayTabla;
    }
}
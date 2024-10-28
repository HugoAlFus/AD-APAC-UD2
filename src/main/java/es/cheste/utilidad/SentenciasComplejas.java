package es.cheste.utilidad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class SentenciasComplejas {

    private static final Logger LOGGER = LogManager.getLogger(SentenciasComplejas.class);

    public static void ejecutarCalcularTotalGasto(){
        try(Connection connection = new ConexionBD().getConnection();
        CallableStatement cs = connection.prepareCall(SentenciasSQL.getSentencia(""))) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

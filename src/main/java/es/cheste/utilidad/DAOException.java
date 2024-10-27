package es.cheste.utilidad;

/**
 * Excepción personalizada para manejar errores relacionados con operaciones DAO.
 * <p>
 * Esta excepción se utiliza para encapsular errores específicos que ocurren
 * durante las operaciones de acceso a datos.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
public class DAOException extends Exception {

    /**
     * Constructor para crear una nueva instancia de DAOException.
     *
     * @param message Mensaje descriptivo del error.
     * @param cause   La causa original del error.
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
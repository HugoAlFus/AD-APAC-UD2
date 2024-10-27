package es.cheste.dao;

import es.cheste.entidad.Mesa;
import es.cheste.utilidad.DAOException;

import java.util.List;

/**
 * Interface MesaDAO
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Mesa.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public interface MesaDAO {

    /**
     * Inserta una nueva mesa en la base de datos.
     *
     * @param mesa El objeto Mesa a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    void insertar(Mesa mesa) throws DAOException;

    /**
     * Obtiene una mesa por su ID.
     *
     * @param idMesa El ID de la mesa a obtener.
     * @return El objeto Mesa correspondiente al ID proporcionado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    Mesa obtenerPorID(int idMesa) throws DAOException;

    /**
     * Obtiene una lista de todas las mesas.
     *
     * @return Una lista de objetos Mesa.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    List<Mesa> obtenerTodos() throws DAOException;

    /**
     * Actualiza la información de una mesa existente.
     *
     * @param mesa El objeto Mesa con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    void actualizar(Mesa mesa) throws DAOException;

    /**
     * Elimina una mesa por su ID.
     *
     * @param idMesa El ID de la mesa a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    void eliminar(int idMesa) throws DAOException;

}

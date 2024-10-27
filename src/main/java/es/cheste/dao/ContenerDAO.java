package es.cheste.dao;

import es.cheste.entidad.Contener;
import es.cheste.utilidad.DAOException;

import java.util.List;

/**
 * Interface ContenerDAO
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Contener.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public interface ContenerDAO {

    /**
     * Inserta un nuevo registro en la base de datos.
     *
     * @param contener El objeto Contener a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    void insertar(Contener contener) throws DAOException;

    /**
     * Obtiene un registro por su ID de pedido y ID de plato.
     *
     * @param idPedido El ID del pedido.
     * @param idPlato  El ID del plato.
     * @return El objeto Contener correspondiente a los IDs insertados.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    Contener obtenerPorID(int idPedido, int idPlato) throws DAOException;

    /**
     * Obtiene una lista de todos los registros.
     *
     * @return Una lista de objetos Contener.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    List<Contener> obtenerTodos() throws DAOException;

    /**
     * Actualiza la información de un registro existente.
     *
     * @param contener El objeto Contener con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    void actualizar(Contener contener) throws DAOException;

    /**
     * Elimina un registro por su ID de pedido y ID de plato.
     *
     * @param idPedido El ID del pedido.
     * @param idPlato  El ID del plato.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    void eliminar(int idPedido, int idPlato) throws DAOException;
}

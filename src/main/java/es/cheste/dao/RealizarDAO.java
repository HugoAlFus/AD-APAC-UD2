package es.cheste.dao;

import es.cheste.entidad.Realizar;
import es.cheste.utilidad.DAOException;

import java.util.List;

/**
 * Interface RealizarDAO
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Realizar.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public interface RealizarDAO {

    /**
     * Inserta un nuevo registro en la base de datos.
     *
     * @param realizar El objeto Realizar a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    void insertar(Realizar realizar) throws DAOException;

    /**
     * Obtiene un registro por su ID de plato y ID de chef.
     *
     * @param idPlato El ID del plato.
     * @param idChef  El ID del chef.
     * @return El objeto Realizar correspondiente a los IDs proporcionados.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    Realizar obtenerPorID(int idPlato, int idChef) throws DAOException;

    /**
     * Obtiene una lista de todos los registros.
     *
     * @return Una lista de objetos Realizar.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    List<Realizar> obtenerTodos() throws DAOException;

    /**
     * Actualiza la información de un registro existente.
     *
     * @param realizar El objeto Realizar con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    void actualizar(Realizar realizar) throws DAOException;

    /**
     * Elimina un registro por su ID de plato y ID de chef.
     *
     * @param idPlato El ID del plato.
     * @param idChef  El ID del chef.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    void eliminar(int idPlato, int idChef) throws DAOException;

}

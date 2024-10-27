package es.cheste.dao;

import es.cheste.entidad.Plato;
import es.cheste.utilidad.DAOException;

import java.util.List;

/**
 * Interface PlatoDAO
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Plato.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public interface PlatoDAO {

    /**
     * Inserta un nuevo plato en la base de datos.
     *
     * @param plato El objeto Plato a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    void insertar(Plato plato) throws DAOException;

    /**
     * Obtiene un plato por su ID.
     *
     * @param idPlato El ID del plato a obtener.
     * @return El objeto Plato correspondiente al ID proporcionado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    Plato obtenerPorID(int idPlato) throws DAOException;

    /**
     * Obtiene una lista de todos los platos.
     *
     * @return Una lista de objetos Plato.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    List<Plato> obtenerTodos() throws DAOException;

    /**
     * Actualiza la información de un plato existente.
     *
     * @param plato El objeto Plato con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    void actualizar(Plato plato) throws DAOException;

    /**
     * Elimina un plato por su ID.
     *
     * @param idPlato El ID del plato a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    void eliminar(int idPlato) throws DAOException;

}

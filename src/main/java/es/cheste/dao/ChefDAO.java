package es.cheste.dao;

import es.cheste.entidad.Chef;
import es.cheste.utilidad.DAOException;

import java.util.List;

/**
 * Interface ChefDAO
 *
 * Proporciona métodos para realizar operaciones CRUD en la entidad Chef.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public interface ChefDAO {

    /**
     * Inserta un nuevo chef en la base de datos.
     *
     * @param chef El objeto Chef a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    void insertar(Chef chef) throws DAOException;

    /**
     * Obtiene un chef por su ID.
     *
     * @param idChef El ID del chef a obtener.
     * @return El objeto Chef correspondiente al ID proporcionado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    Chef obtenerPorID(int idChef) throws DAOException;

    /**
     * Obtiene una lista de todos los chefs.
     *
     * @return Una lista de objetos Chef.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    List<Chef> obtenerTodos() throws DAOException;

    /**
     * Actualiza la información de un chef existente.
     *
     * @param chef El objeto Chef con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    void actualizar(Chef chef) throws DAOException;

    /**
     * Elimina un chef por su ID.
     *
     * @param idChef El ID del chef a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    void eliminar(int idChef) throws DAOException;

}


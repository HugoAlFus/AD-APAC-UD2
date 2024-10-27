package es.cheste.dao;

import es.cheste.entidad.Cliente;
import es.cheste.utilidad.DAOException;

import java.util.List;

/**
 * Interface ClienteDAO
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Cliente.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public interface ClienteDAO {

    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param cliente El objeto Cliente a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    void insertar(Cliente cliente) throws DAOException;

    /**
     * Obtiene un cliente por su ID.
     *
     * @param idCliente El ID del cliente a obtener.
     * @return El objeto Cliente correspondiente al ID proporcionado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    Cliente obtenerPorID(int idCliente) throws DAOException;

    /**
     * Obtiene una lista de todos los clientes.
     *
     * @return Una lista de objetos Cliente.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    List<Cliente> obtenerTodos() throws DAOException;

    /**
     * Actualiza la información de un cliente existente.
     *
     * @param cliente El objeto Cliente con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    void actualizar(Cliente cliente) throws DAOException;

    /**
     * Elimina un cliente por su ID.
     *
     * @param idCliente El ID del cliente a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    void eliminar(int idCliente) throws DAOException;
}

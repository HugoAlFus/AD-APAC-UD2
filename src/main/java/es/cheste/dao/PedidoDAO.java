package es.cheste.dao;

import es.cheste.entidad.Pedido;
import es.cheste.utilidad.DAOException;

import java.util.List;

/**
 * Interface PedidoDAO
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Pedido.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public interface PedidoDAO {

    /**
     * Inserta un nuevo pedido en la base de datos.
     *
     * @param pedido El objeto Pedido a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    void insertar(Pedido pedido) throws DAOException;

    /**
     * Obtiene un pedido por su ID.
     *
     * @param idPedido El ID del pedido a obtener.
     * @return El objeto Pedido correspondiente al ID proporcionado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    Pedido obtenerPorID(int idPedido) throws DAOException;

    /**
     * Obtiene una lista de todos los pedidos.
     *
     * @return Una lista de objetos Pedido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    List<Pedido> obtenerTodos() throws DAOException;

    /**
     * Actualiza la información de un pedido existente.
     *
     * @param pedido El objeto Pedido con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    void actualizar(Pedido pedido) throws DAOException;

    /**
     * Elimina un pedido por su ID.
     *
     * @param idPedido El ID del pedido a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    void eliminar(int idPedido) throws DAOException;
}
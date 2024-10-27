package es.cheste.servicio;

import es.cheste.dao.PedidoDAO;
import es.cheste.dao.impl.PedidoDAOImpl;
import es.cheste.entidad.Pedido;
import es.cheste.entidad.enums.EstadoPedido;
import es.cheste.utilidad.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con pedidos.
 * <p>
 * Proporciona métodos para agregar, obtener, listar, actualizar y eliminar pedidos.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
public class PedidoServicio {

    private static final Logger LOGGER = LogManager.getLogger(PedidoServicio.class);
    private PedidoDAO pedidoDAO;

    /**
     * Constructor por defecto que inicializa el DAO de Pedido.
     */
    public PedidoServicio() {
        this.pedidoDAO = new PedidoDAOImpl();
    }

    /**
     * Agrega un nuevo pedido.
     *
     * @param fechaPedido  Fecha del pedido.
     * @param precioTotal  Precio total del pedido.
     * @param idCliente    ID del cliente que realiza el pedido.
     * @param idMesa       ID de la mesa asociada al pedido.
     * @param estadoPedido Estado del pedido.
     */
    public void agregarPedido(LocalDate fechaPedido, double precioTotal, int idCliente, int idMesa, EstadoPedido estadoPedido) {
        Pedido pedido = new Pedido(fechaPedido, precioTotal, idCliente, idMesa, estadoPedido);

        try {
            pedidoDAO.insertar(pedido);
            System.out.println("Pedido agregado con ID: " + pedido.getIdPedido());
        } catch (DAOException e) {
            System.err.println("El pedido no se pudo agregar");
            LOGGER.error("No se agregó el Pedido: {}", e.getMessage());
        }
    }

    /**
     * Obtiene un pedido por su ID.
     *
     * @param idPedido ID del pedido.
     * @return El pedido correspondiente al ID proporcionado.
     */
    public Pedido obtenerPedido(int idPedido) {
        try {
            return pedidoDAO.obtenerPorID(idPedido);
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener el pedido: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Lista todos los pedidos.
     *
     * @return Lista de todos los pedidos.
     */
    public List<Pedido> listarPedidos() {
        try {
            return pedidoDAO.obtenerTodos();
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener todos los pedidos: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza la información de un pedido.
     *
     * @param idPedido     ID del pedido.
     * @param fechaPedido  Fecha del pedido.
     * @param precioTotal  Precio total del pedido.
     * @param idCliente    ID del cliente que realiza el pedido.
     * @param idMesa       ID de la mesa asociada al pedido.
     * @param estadoPedido Estado del pedido.
     */
    public void actualizarPedido(int idPedido, LocalDate fechaPedido, double precioTotal, int idCliente, int idMesa, EstadoPedido estadoPedido) {
        Pedido pedido = new Pedido(idPedido, fechaPedido, precioTotal, idCliente, idMesa, estadoPedido);

        try {
            pedidoDAO.actualizar(pedido);
            System.out.println("Pedido actualizado: " + pedido);
        } catch (DAOException e) {
            System.err.println("El pedido no se pudo actualizar");
            LOGGER.error("No se actualizó el pedido: {}", e.getMessage());
        }
    }

    /**
     * Elimina un pedido por su ID.
     *
     * @param idPedido ID del pedido.
     */
    public void eliminarPedido(int idPedido) {
        try {
            pedidoDAO.eliminar(idPedido);
            System.out.println("Pedido eliminado con ID " + idPedido);
        } catch (DAOException e) {
            System.err.println("Hubo un error al intentar eliminar el pedido");
            LOGGER.error("Hubo un error al eliminar el pedido: {}", e.getMessage());
        }
    }
}
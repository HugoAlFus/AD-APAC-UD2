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

public class PedidoServicio {

    private static final Logger LOGGER = LogManager.getLogger(PedidoServicio.class);
    private PedidoDAO pedidoDAO;

    public PedidoServicio() {
        this.pedidoDAO = new PedidoDAOImpl();
    }

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

    public Pedido obtenerPedido(int idPedido) {
        try {
            return pedidoDAO.obtenerPorID(idPedido);
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener el pedido: {}", e.getMessage());
            return null;
        }
    }

    public List<Pedido> listarPedidos() {
        try {
            return pedidoDAO.obtenerTodos();
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener todos los pedidos: {}", e.getMessage());
            return null;
        }
    }

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

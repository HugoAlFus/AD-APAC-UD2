package es.cheste.servicio;

import es.cheste.dao.ContenerDAO;
import es.cheste.dao.impl.ContenerDAOImpl;
import es.cheste.entidad.Contener;
import es.cheste.utilidad.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con la entidad Contener.
 * <p>
 * Proporciona métodos para agregar, obtener, listar, actualizar y eliminar registros de Contener.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
public class ContenerServicio {

    private static final Logger LOGGER = LogManager.getLogger(ContenerServicio.class);
    private ContenerDAO contenerDAO;

    /**
     * Constructor por defecto que inicializa el DAO de Contener.
     */
    public ContenerServicio() {
        this.contenerDAO = new ContenerDAOImpl();
    }

    /**
     * Agrega un nuevo registro de Contener.
     *
     * @param idPedido ID del pedido.
     * @param idPlato  ID del plato.
     * @param cantidad Cantidad del plato en el pedido.
     * @param subtotal Subtotal del plato en el pedido.
     */
    public void agregarContener(int idPedido, int idPlato, int cantidad, double subtotal) {
        Contener contener = new Contener(idPedido, idPlato, cantidad, subtotal);

        try {
            contenerDAO.insertar(contener);
            System.out.println("Contener agregado con ID de pedido: " + contener.getIdPedido() + " y con ID de plato: " + contener.getIdPlato());
        } catch (DAOException e) {
            System.err.println("contener no se pudo agregar");
            LOGGER.error("No se agregó contener {}", e.getMessage());
        }
    }

    /**
     * Obtiene un registro de Contener por su ID de pedido y ID de plato.
     *
     * @param idPedido ID del pedido.
     * @param idPlato  ID del plato.
     * @return El registro de Contener correspondiente a los IDs proporcionados.
     */
    public Contener obtenerContener(int idPedido, int idPlato) {
        try {
            return contenerDAO.obtenerPorID(idPedido, idPlato);
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener contener {}", e.getMessage());
            return null;
        }
    }

    /**
     * Lista todos los registros de Contener.
     *
     * @return Lista de todos los registros de Contener.
     */
    public List<Contener> listarContener() {
        try {
            return contenerDAO.obtenerTodos();
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener todos los contener {}", e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza un registro de Contener.
     *
     * @param idPedido ID del pedido.
     * @param idPlato  ID del plato.
     * @param cantidad Cantidad del plato en el pedido.
     * @param subtotal Subtotal del plato en el pedido.
     */
    public void actualizarContener(int idPedido, int idPlato, int cantidad, double subtotal) {
        Contener contener = new Contener(idPedido, idPlato, cantidad, subtotal);

        try {
            contenerDAO.actualizar(contener);
            System.out.println("Contener actualizado: " + contener);
        } catch (DAOException e) {
            System.err.println("El contener no se pudo actualizar");
            LOGGER.error("No se actualizó contener {}", e.getMessage());
        }
    }

    /**
     * Elimina un registro de Contener por su ID de pedido y ID de plato.
     *
     * @param idPedido ID del pedido.
     * @param idPlato  ID del plato.
     */
    public void eliminarContener(int idPedido, int idPlato) {
        try {
            contenerDAO.eliminar(idPedido, idPlato);
            System.out.println("Contener eliminado con ID Pedido " + idPedido + " y ID Plato " + idPlato);
        } catch (DAOException e) {
            System.err.println("Hubo un error al intentar eliminar el contener");
            LOGGER.error("Hubo un error al eliminar el contener {}", e.getMessage());
        }
    }
}
package es.cheste.servicio;

import es.cheste.dao.ContenerDAO;
import es.cheste.dao.impl.ContenerDAOImpl;
import es.cheste.entidad.Contener;
import es.cheste.utilidad.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ContenerServicio {

    private static final Logger LOGGER = LogManager.getLogger(ContenerServicio.class);
    private ContenerDAO contenerDAO;

    public ContenerServicio() {
        this.contenerDAO = new ContenerDAOImpl();
    }

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

    public Contener obtenerContener(int idPedido, int idPlato) {
        try {
            return contenerDAO.obtenerPorID(idPedido, idPlato);
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener contener {}", e.getMessage());
            return null;
        }
    }

    public List<Contener> listarContener() {
        try {
            return contenerDAO.obtenerTodos();
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener todos los contener {}", e.getMessage());
            return null;
        }
    }

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
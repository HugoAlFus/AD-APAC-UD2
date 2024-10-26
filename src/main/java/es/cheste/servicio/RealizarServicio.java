package es.cheste.servicio;

import es.cheste.dao.RealizarDAO;
import es.cheste.dao.impl.RealizarDAOImpl;
import es.cheste.entidad.Realizar;
import es.cheste.utilidad.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class RealizarServicio {

    private static final Logger LOGGER = LogManager.getLogger(RealizarServicio.class);
    private RealizarDAO realizarDAO;

    public RealizarServicio() {
        this.realizarDAO = new RealizarDAOImpl();
    }

    public void agregarRealizar(int idPlato, int idChef, LocalDate fecha) {
        Realizar realizar = new Realizar(idPlato, idChef, fecha);

        try {
            realizarDAO.insertar(realizar);
            System.out.println("Realización agregada con ID de plato : " + realizar.getIdPlato() + " y ID de chef: " + realizar.getIdChef());
        } catch (DAOException e) {
            System.err.println("No se pudo registrar la realización");
            LOGGER.error("No se registró la Realización: {}", e.getMessage());
        }
    }

    public Realizar obtenerRealizar(int idPlato, int idChef) {
        try {
            return realizarDAO.obtenerPorID(idPlato, idChef);
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener la realización: {}", e.getMessage());
            return null;
        }
    }

    public List<Realizar> listarRealizaciones() {
        try {
            return realizarDAO.obtenerTodos();
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener todas las realizaciones: {}", e.getMessage());
            return null;
        }
    }

    public void actualizarRealizar(int idPlato, int idChef, LocalDate fecha) {
        Realizar realizar = new Realizar(idPlato, idChef, fecha);

        try {
            realizarDAO.actualizar(realizar);
            System.out.println("Realización actualizada: " + realizar);
        } catch (DAOException e) {
            System.err.println("No se pudo actualizar la realización");
            LOGGER.error("No se actualizó la realización: {}", e.getMessage());
        }
    }

    public void eliminarRealizar(int idPlato, int idChef) {
        try {
            realizarDAO.eliminar(idPlato, idChef);
            System.out.println("Realización eliminada con ID de plato: " + idPlato + " y con ID de chef: " +idChef);
        } catch (DAOException e) {
            System.err.println("Hubo un error al intentar eliminar la realización");
            LOGGER.error("Hubo un error al eliminar la realización: {}", e.getMessage());
        }
    }
}

package es.cheste.servicio;

import es.cheste.dao.RealizarDAO;
import es.cheste.dao.impl.RealizarDAOImpl;
import es.cheste.entidad.Realizar;
import es.cheste.utilidad.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con la entidad Realizar.
 * <p>
 * Proporciona métodos para agregar, obtener, listar, actualizar y eliminar registros de Realizar.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
public class RealizarServicio {

    private static final Logger LOGGER = LogManager.getLogger(RealizarServicio.class);
    private RealizarDAO realizarDAO;

    /**
     * Constructor por defecto que inicializa el DAO de Realizar.
     */
    public RealizarServicio() {
        this.realizarDAO = new RealizarDAOImpl();
    }

    /**
     * Agrega un nuevo registro de Realizar.
     *
     * @param idPlato ID del plato.
     * @param idChef  ID del chef.
     * @param fecha   Fecha de la realización.
     */
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

    /**
     * Obtiene un registro de Realizar por su ID de plato y ID de chef.
     *
     * @param idPlato ID del plato.
     * @param idChef  ID del chef.
     * @return El registro de Realizar correspondiente a los IDs proporcionados.
     */
    public Realizar obtenerRealizar(int idPlato, int idChef) {
        try {
            return realizarDAO.obtenerPorID(idPlato, idChef);
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener la realización: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Lista todos los registros de Realizar.
     *
     * @return Lista de todos los registros de Realizar.
     */
    public List<Realizar> listarRealizaciones() {
        try {
            return realizarDAO.obtenerTodos();
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener todas las realizaciones: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza un registro de Realizar.
     *
     * @param idPlato ID del plato.
     * @param idChef  ID del chef.
     * @param fecha   Fecha de la realización.
     */
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

    /**
     * Elimina un registro de Realizar por su ID de plato y ID de chef.
     *
     * @param idPlato ID del plato.
     * @param idChef  ID del chef.
     */
    public void eliminarRealizar(int idPlato, int idChef) {
        try {
            realizarDAO.eliminar(idPlato, idChef);
            System.out.println("Realización eliminada con ID de plato: " + idPlato + " y con ID de chef: " + idChef);
        } catch (DAOException e) {
            System.err.println("Hubo un error al intentar eliminar la realización");
            LOGGER.error("Hubo un error al eliminar la realización: {}", e.getMessage());
        }
    }
}